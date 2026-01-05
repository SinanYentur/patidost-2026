package com.patidost.app.domain.repository

import com.patidost.app.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Auth Repository - V10000.8100 Social Identity Edition.
 * Rule 108: Android 15 (API 35) & Credential Manager 2025.
 */
interface AuthRepository {
    
    /**
     * Re-active session stream - Single Source of Truth.
     */
    fun getCurrentUser(): Flow<User?>

    /**
     * Standard Auth with Play Integrity verification.
     */
    suspend fun signIn(email: String, password: String): Result<User>

    /**
     * Social Auth: Google Sign-In via Credential Manager.
     */
    suspend fun signInWithGoogle(): Result<User>

    /**
     * Social Auth: Facebook Login Integration.
     */
    suspend fun signInWithFacebook(): Result<User>

    /**
     * Social Auth: Instagram/Meta Identity Integration.
     */
    suspend fun signInWithInstagram(): Result<User>

    /**
     * Registration with Data Minimization.
     */
    suspend fun signUp(email: String, password: String, name: String): Result<User>

    /**
     * Session purge and cache cleanup.
     */
    suspend fun signOut(): Result<Unit>

    /**
     * GDPR Art. 17: Right to Erasure.
     */
    suspend fun deleteAccount(): Result<Unit>

    /**
     * Play Integrity Handshake.
     */
    suspend fun getIntegrityToken(nonce: String): Result<String>
}
