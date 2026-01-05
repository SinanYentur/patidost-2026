package com.patidost.app.ui.screen.auth.login

import com.patidost.app.domain.model.User

/**
 * LoginUiState - V10000.9600 Sovereign SSOT.
 * Updated to support both resource IDs and raw error messages.
 */
sealed interface LoginUiState {
    object Initial : LoginUiState
    object Loading : LoginUiState
    data class Authenticated(val user: User) : LoginUiState
    object Unauthenticated : LoginUiState
    data class Error(val messageResId: Int) : LoginUiState
    data class ErrorMsg(val message: String) : LoginUiState // 🛡️ Added for Firebase raw error display
}
