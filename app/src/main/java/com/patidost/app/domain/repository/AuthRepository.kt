package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User

/**
 * The main interface for authentication-related operations.
 * This abstracts the data layer from the domain layer.
 */
interface AuthRepository {
    /**
     * Signs up a user with email and password.
     */
    suspend fun signUpWithEmail(email: String, password: String, name: String): Resource<Unit>

    /**
     * Signs in a user with email and password.
     */
    suspend fun signInWithEmail(email: String, password: String): Resource<User>

    /**
     * Signs in a user with a Google ID token.
     */
    suspend fun signInWithGoogle(idToken: String): Resource<User>

    /**
     * Retrieves the currently signed-in user's data.
     */
    suspend fun getCurrentUser(): Resource<User>

    /**
     * Signs out the current user.
     */
    suspend fun signOut(): Resource<Unit>

    /**
     * Gets the current user's ID, or null if not signed in.
     */
    fun getCurrentUserId(): String?
}
