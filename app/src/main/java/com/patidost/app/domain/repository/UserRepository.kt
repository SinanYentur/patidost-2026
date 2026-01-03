package com.patidost.app.domain.repository

import com.patidost.app.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Unified UserRepository Contract (SSOT) - 2026 PRODUCTION Standard.
 * RVWL: Expanded with Profile Update capabilities for Phase 9.
 */
interface UserRepository {
    
    /** Streams current session status. */
    fun getCurrentUser(): Flow<User?>

    /** Fetches user information by ID. */
    fun getUserById(userId: String): Flow<User?>

    /** Updates user profile metadata (Bio, PhotoUrl). */
    suspend fun updateProfile(userId: String, name: String, bio: String, photoUrl: String): Result<Unit>

    /** Authenticates a user. */
    suspend fun signIn(email: String, pass: String): Result<User>

    /** Registers a new user. */
    suspend fun signUp(email: String, pass: String, name: String): Result<User>

    /** Signs out from the current provider. */
    suspend fun signOut(): Result<Unit>

    /** Deletes the user account and associated data. */
    suspend fun deleteAccount(): Result<Unit>
}
