package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.presentation.ui.util.UiText

class FakeAuthRepository : AuthRepository {

    private var shouldReturnError = false
    private var signedInUser: User? = null

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun signInWithEmail(email: String, password: String): Resource<User> {
        return if (shouldReturnError) {
            Resource.Error(UiText.DynamicString("Fake sign-in error"))
        } else {
            val user = User(
                uid = "fake-id", 
                name = "Test User", 
                email = "test@test.com", 
                avatarUrl = "", 
                patiPoints = 100,
                status = "active",
                verificationLevel = 2
            )
            signedInUser = user
            Resource.Success(user)
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Resource<User> {
        return if (shouldReturnError) {
            Resource.Error(UiText.DynamicString("Fake Google sign-in error"))
        } else {
            val user = User(
                uid = "fake-google-id",
                name = "Google Test User",
                email = "google@test.com",
                avatarUrl = "",
                patiPoints = 100,
                status = "active",
                verificationLevel = 2
            )
            signedInUser = user
            Resource.Success(user)
        }
    }

    override suspend fun signUpWithEmail(email: String, password: String, name: String): Resource<Unit> {
        return if (shouldReturnError) {
            Resource.Error(UiText.DynamicString("Fake sign-up error"))
        } else {
            val user = User(
                uid = "fake-id", 
                name = name, 
                email = email, 
                avatarUrl = "", 
                patiPoints = 0,
                status = "active",
                verificationLevel = 1
            )
            signedInUser = user
            Resource.Success(Unit)
        }
    }

    override suspend fun signOut(): Resource<Unit> {
        signedInUser = null
        return Resource.Success(Unit)
    }

    override fun getCurrentUserId(): String? {
        return signedInUser?.uid
    }

    override suspend fun getCurrentUser(): Resource<User> {
        return signedInUser?.let { Resource.Success(it) } ?: Resource.Error(UiText.DynamicString("No user signed in"))
    }
}
