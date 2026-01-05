package com.patidost.app.presentation.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.util.AuthError

sealed interface AuthUiState {
    object Idle : AuthUiState
    object Loading : AuthUiState
    data class Authenticated(val user: User) : AuthUiState
    data class Error(val error: AuthError) : AuthUiState
}

sealed interface AuthEffect {
    object NavigateToHome : AuthEffect()
    data class ShowToast(val message: String) : AuthEffect()
}
