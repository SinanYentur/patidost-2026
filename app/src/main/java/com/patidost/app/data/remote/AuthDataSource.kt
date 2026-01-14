package com.patidost.app.data.remote

import com.patidost.app.core.util.Resource

data class AuthResult(val uid: String, val isNewUser: Boolean)

interface AuthDataSource {
    suspend fun signUp(email: String, password: String, name: String): Resource<Unit>

    suspend fun login(email: String, password: String): Resource<String>

    suspend fun signInWithGoogle(idToken: String): Resource<AuthResult>

    suspend fun logout()

    fun getCurrentUserId(): String?
}
