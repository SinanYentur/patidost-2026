package com.patidost.app.data.remote

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User

/**
 * Interface for authentication data source.
 * Defines the contract for all authentication-related operations.
 */
interface AuthDataSource {
    suspend fun signUp(email: String, password: String): Resource<String> // Returns UID
    suspend fun login(email: String, password: String): Resource<String> // Returns UID
    suspend fun logout()
    fun getCurrentUserId(): String?
}
