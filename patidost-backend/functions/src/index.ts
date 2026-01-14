
import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

// Initialize the Firebase Admin SDK.
admin.initializeApp();
const db = admin.firestore();

/**
 * Anayasal Cloud Function: givePati
 * Atomically transfers a "pati" from a user to a pet.
 * This is a transactional function to ensure data integrity.
 */
export const givePati = functions.https.onCall(async (data, context) => {
  // 1. Anayasal Kural: Kimlik Doğrulama
  // The request must be made by an authenticated user.
  const uid = context.auth?.uid;
  if (!uid) {
    throw new functions.https.HttpsError(
      "unauthenticated",
      "Function must be called while authenticated.",
    );
  }

  // 2. Anayasal Kural: Veri Bütünlüğü
  // The request must contain a valid petId.
  const petId = data.petId;
  if (typeof petId !== "string" || petId.length === 0) {
    throw new functions.https.HttpsError(
      "invalid-argument",
      "Function must be called with a valid 'petId'.",
    );
  }
  const amount = 1; // In this phase, amount is fixed to 1.

  // 3. Anayasal Kural: Atomik İşlem
  // All database operations must succeed or fail together.
  try {
    await db.runTransaction(async (tx) => {
      const walletRef = db.doc(`wallets/${uid}`);
      const petRef = db.doc(`pets/${petId}`);
      const transactionRef = db.collection("pati_transactions").doc();

      const walletSnap = await tx.get(walletRef);
      const walletData = walletSnap.data();

      // Bakiye Kontrolü
      if (!walletData || walletData.patiBalance < amount) {
        throw new functions.https.HttpsError(
          "failed-precondition",
          "Insufficient pati balance.",
        );
      }

      // Atomik Güncellemeler
      tx.update(walletRef, {
        patiBalance: admin.firestore.FieldValue.increment(-amount),
        totalGiven: admin.firestore.FieldValue.increment(amount),
      });

      tx.update(petRef, {
        totalPati: admin.firestore.FieldValue.increment(amount),
      });

      // İşlem Kaydı (Ledger)
      tx.set(transactionRef, {
        fromUserId: uid,
        toPetId: petId,
        amount,
        type: "GIVE_PAW",
        createdAt: admin.firestore.FieldValue.serverTimestamp(),
      });
    });

    return { success: true, message: "Pati successfully given." };
  } catch (error) {
    functions.logger.error("Transaction failed:", error);
    // HttpsError'ları istemciye geri yansıt, diğer hataları gizle.
    if (error instanceof functions.https.HttpsError) {
      throw error;
    }
    throw new functions.https.HttpsError("internal", "An internal error occurred.");
  }
});
