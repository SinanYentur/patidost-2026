package com.patidost.app.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * 🛡️ SessionCache - Sovereign Secure Storage Contract.
 * Defines the contract for securely persisting and retrieving session data, such as auth tokens.
 */
interface SessionCache {
    fun getAuthToken(): Flow<String?>
    suspend fun saveAuthToken(token: String)
    suspend fun clearAuthToken()
}
