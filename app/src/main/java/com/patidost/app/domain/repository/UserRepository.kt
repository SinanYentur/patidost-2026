package com.patidost.app.domain.repository

import com.patidost.app.domain.model.User
import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

/**
 * 🛡️ UserRepository - Sovereign Interface Standard.
 * Rule 100: Mandatory contracts for user data management.
 */
interface UserRepository {
    fun getCurrentUser(): Flow<User?>
    fun getUserProfile(userId: String): Flow<User?>
    suspend fun getUserById(userId: String): DomainResult<User>
    suspend fun updateUserProfile(user: User): Result<Unit>
    
    // 🛡️ Monitoring & Integrity
    fun isDataCollectionEnabled(): Boolean
}
