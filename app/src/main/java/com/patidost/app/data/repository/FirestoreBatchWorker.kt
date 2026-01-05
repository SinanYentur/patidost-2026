package com.patidost.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.data.model.PetEntity
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreBatchWorker @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun bulkAdoptionUpdate(petIds: List<String>) {
        val batch = firestore.batch()
        petIds.forEach { id ->
            val ref = firestore.collection("pets").document(id)
            batch.update(ref, "isAdopted", true)
        }
        batch.commit().await()
    }
}
