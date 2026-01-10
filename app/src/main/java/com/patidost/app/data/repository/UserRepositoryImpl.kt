package com.patidost.app.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.data.local.dao.UserDao
import com.patidost.app.data.local.entity.UserEntity
import com.patidost.app.di.IoDispatcher
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.domain.util.DomainResult
import com.patidost.app.domain.util.AppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 🛡️ UserRepositoryImpl - V10011.70160 Firebase Live Seal.
 * Rule 190: Direct integration with Firebase Auth & Firestore for user data.
 * ARTICLE 34: Removed Auth responsibilities. Now purely manages user data.
 */
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val userDao: UserDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserRepository {

    override fun getCurrentUser(): Flow<User?> = 
        userDao.getUserById(firebaseAuth.currentUser?.uid ?: "").map { it?.toDomain() }

    override fun getUserProfile(userId: String): Flow<User?> =
        userDao.getUserById(userId).map { it?.toDomain() }

    override suspend fun getUserById(userId: String): DomainResult<User> = withContext(ioDispatcher) {
        try {
            val snapshot = firestore.collection("users").document(userId).get().await()
            val user = snapshot.toObject(User::class.java) ?: throw Exception("Kullanıcı bulunamadı.")
            userDao.insertUser(UserEntity.fromDomain(user))
            DomainResult.Success(user)
        } catch (e: Exception) {
            DomainResult.Error(AppError.UnknownError(e.message ?: "Unknown error"))
        }
    }

    override suspend fun updateUserProfile(user: User): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            firestore.collection("users").document(user.id).set(user).await()
            userDao.insertUser(UserEntity.fromDomain(user))
            Unit
        }
    }

    override fun isDataCollectionEnabled(): Boolean = true
}
