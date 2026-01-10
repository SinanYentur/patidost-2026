package com.patidost.app.domain.repository

import com.patidost.app.domain.model.User
import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

/**
 * 🛡️ AuthRepository - V10000.70019 Sovereign Standard.
 * Rule 108: Defines contracts for authentication and session management.
 * V3: Added onboardUser contract.
 */
interface AuthRepository {
    fun getCurrentUser(): Flow<User?>
    suspend fun signIn(email: String, password: String): DomainResult<User>
    suspend fun signUp(email: String, password: String, name: String): DomainResult<User>
    suspend fun onboardUser(name: String, birthDate: String, hasPet: Boolean): DomainResult<Unit>
    suspend fun signOut(): DomainResult<Unit>
    suspend fun deleteAccount(): DomainResult<Unit>
    suspend fun signInWithGoogle(): DomainResult<User>
    suspend fun signInWithFacebook(): DomainResult<User>
    suspend fun signInWithInstagram(): DomainResult<User>
    suspend fun getIntegrityToken(nonce: String): DomainResult<String>
}
