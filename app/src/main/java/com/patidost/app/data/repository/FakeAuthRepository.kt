package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.PatiUser
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject

class FakeAuthRepository @Inject constructor() : AuthRepository {

    private var currentUser: PatiUser? = PatiUser(
        uid = "12345",
        name = "Kaan Ayhan",
        email = "kaan.ayhan@example.com",
        avatarUrl = "https://i.pravatar.cc/150?u=kaanayhan"
    )

    var shouldReturnError = false

    override suspend fun signUpWithEmail(email: String, password: String, name: String): Resource<Unit> {
        delay(1000)
        if (shouldReturnError) return Resource.Error("Sign up failed.")
        return Resource.Success(Unit)
    }

    override suspend fun signInWithEmail(email: String, password: String): Resource<Unit> {
        delay(1000)
        if (shouldReturnError) return Resource.Error("Sign in failed.")
        return Resource.Success(Unit)
    }

    override suspend fun getCurrentUser(): Resource<PatiUser> {
        delay(500)
        if (shouldReturnError) return Resource.Error("Failed to fetch user.")
        return currentUser?.let { Resource.Success(it) } ?: Resource.Error("No user logged in.")
    }

    override suspend fun signOut(): Resource<Unit> {
        delay(500)
        if (shouldReturnError) return Resource.Error("Sign out failed.")
        currentUser = null
        return Resource.Success(Unit)
    }

    override fun getCurrentUserId(): String? {
        return currentUser?.uid
    }
}
