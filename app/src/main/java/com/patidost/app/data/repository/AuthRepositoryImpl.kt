package com.patidost.app.data.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.remote.AuthDataSource
import com.patidost.app.data.remote.AuthResult
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userRepository: UserRepository,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun signInWithEmail(email: String, password: String): Resource<User> {
        return when (val loginResult = authDataSource.login(email, password)) {
            is Resource.Success -> {
                val userId = loginResult.data
                if (userId == null) {
                    Resource.Error(UiText.DynamicString("Authentication failed: User ID is null."))
                } else {
                    userRepository.getUser(userId, forceRefresh = true)
                }
            }
            is Resource.Error -> {
                Resource.Error(loginResult.message ?: UiText.DynamicString("An unknown error occurred."))
            }
            is Resource.Loading -> {
                Resource.Loading()
            }
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Resource<User> {
        return when (val loginResult = authDataSource.signInWithGoogle(idToken)) {
            is Resource.Success -> {
                val authResult = loginResult.data
                if (authResult == null) {
                    return Resource.Error(UiText.DynamicString("Google Sign-In failed: Auth result is null."))
                }
                // if (authResult.isNewUser) {
                //     createInitialWallet(authResult.uid)
                // }
                userRepository.getUser(authResult.uid, forceRefresh = true)
            }
            is Resource.Error -> {
                Resource.Error(loginResult.message ?: UiText.DynamicString("An unknown error occurred during Google Sign-In."))
            }
            is Resource.Loading -> Resource.Loading()
        }
    }

    override suspend fun signUpWithEmail(email: String, password: String, name: String): Resource<Unit> {
        val signUpResult = authDataSource.signUp(email, password, name)
        if (signUpResult is Resource.Success) {
            val userId = authDataSource.getCurrentUserId()
            // if (userId != null) {
            //     createInitialWallet(userId)
            // }
        }
        return signUpResult
    }

    private suspend fun createInitialWallet(userId: String) {
        val walletData = mapOf(
            "patiBalance" to 5L, // Initial pati balance
            "totalGiven" to 0L,
            "totalReceived" to 0L,
            "updatedAt" to FieldValue.serverTimestamp()
        )
        try {
            firestore.collection("wallets").document(userId).set(walletData).await()
        } catch (e: Exception) {
            println("Failed to create initial wallet for user $userId: ${e.message}")
        }
    }

    override suspend fun signOut(): Resource<Unit> {
        authDataSource.logout()
        return Resource.Success(Unit)
    }

    override fun getCurrentUserId(): String? {
        return authDataSource.getCurrentUserId()
    }

    override suspend fun getCurrentUser(): Resource<User> {
        val currentUserId = getCurrentUserId()
            ?: return Resource.Error(UiText.DynamicString("No active session found."))
        return userRepository.getUser(currentUserId, forceRefresh = false)
    }
}
