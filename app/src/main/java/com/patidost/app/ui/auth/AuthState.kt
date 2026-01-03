package com.patidost.app.ui.auth

import com.patidost.app.domain.model.User

/**
 * Deterministic UI State for Authentication.
 * RVWL: Representing exclusive states per Android MAD guidelines.
 */
sealed interface AuthState {
    /** Initial state during session verification. */
    object Loading : AuthState

    /** Session verified, user is logged in. */
    data class Authenticated(val user: User) : AuthState

    /** No active session, user is a guest. */
    object Unauthenticated : AuthState

    /** An error occurred during auth operations. */
    data class Error(val message: String) : AuthState
}
