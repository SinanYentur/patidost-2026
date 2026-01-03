package com.patidost.app.data.repository

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Production-ready UserRepository Implementation (SSOT) - 2026 Standard.
 * RVWL: Synchronized with com.patidost.app package identity and Profile Expansion.
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    override fun getCurrentUser(): Flow<User?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            val user = firebaseUser?.let {
                User(
                    id = it.uid,
                    email = it.email ?: "",
                    displayName = it.displayName ?: "",
                    photoUrl = it.photoUrl?.toString() ?: "",
                    phoneNumber = it.phoneNumber ?: "",
                    bio = "",
                    isVerified = it.isEmailVerified,
                    createdAt = it.metadata?.creationTimestamp ?: 0L
                )
            }
            trySend(user)
        }
        firebaseAuth.addAuthStateListener(listener)
        awaitClose { firebaseAuth.removeAuthStateListener(listener) }
    }

    override fun getUserById(userId: String): Flow<User?> = callbackFlow {
        val docRef = firestore.collection("users").document(userId)
        val listener = docRef.addSnapshotListener { snapshot, _ ->
            val user = snapshot?.toObject(User::class.java)
            trySend(user)
        }
        awaitClose { listener.remove() }
    }

    override suspend fun updateProfile(userId: String, name: String, bio: String, photoUrl: String): Result<Unit> = runCatching {
        val updateMap = mapOf(
            "displayName" to name,
            "bio" to bio,
            "photoUrl" to photoUrl
        )
        firestore.collection("users").document(userId).set(updateMap, com.google.firebase.firestore.SetOptions.merge()).await()
    }

    override suspend fun signIn(email: String, pass: String): Result<User> = runCatching {
        val result = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
        val firebaseUser = result.user ?: throw Exception("Giriş başarısız.")
        User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            displayName = firebaseUser.displayName ?: "",
            photoUrl = firebaseUser.photoUrl?.toString() ?: "",
            phoneNumber = firebaseUser.phoneNumber ?: "",
            bio = "",
            isVerified = firebaseUser.isEmailVerified,
            createdAt = firebaseUser.metadata?.creationTimestamp ?: 0L
        )
    }

    override suspend fun signUp(email: String, pass: String, name: String): Result<User> = runCatching {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
        val firebaseUser = result.user ?: throw Exception("Kayıt başarısız.")
        val user = User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            displayName = name,
            photoUrl = "",
            phoneNumber = "",
            bio = "",
            isVerified = false,
            createdAt = firebaseUser.metadata?.creationTimestamp ?: 0L
        )
        // Ensure user record is created in Firestore
        firestore.collection("users").document(user.id).set(user).await()
        user
    }

    override suspend fun signOut(): Result<Unit> = runCatching {
        firebaseAuth.signOut()
    }

    override suspend fun deleteAccount(): Result<Unit> = runCatching {
        firebaseAuth.currentUser?.delete() ?: throw Exception("No user logged in")
    }
}
