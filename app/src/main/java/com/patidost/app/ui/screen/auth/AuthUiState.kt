package com.patidost.app.ui.screen.auth

import androidx.annotation.StringRes
import com.patidost.app.domain.model.User

/**
 * Auth UI State - 2026 PRODUCTION Standard.
 * RVWL: Synchronized with new package identity and localized resources.
 * WEB PROOF: Android UI State Guidelines (Rule 35).
 */
sealed interface AuthUiState {
    object Initial : AuthUiState
    object Loading : AuthUiState
    
    data class Authenticated(val user: User) : AuthUiState
    
    object Unauthenticated : AuthUiState
    
    /**
     * Error state holds a string resource ID.
     */
    data class Error(
        @StringRes val messageResId: Int,
        val isLoginMode: Boolean = true
    ) : AuthUiState
}
