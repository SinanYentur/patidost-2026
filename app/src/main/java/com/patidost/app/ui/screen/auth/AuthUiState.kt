package com.patidost.app.ui.screen.auth

sealed interface AuthUiState {
    object Idle : AuthUiState
    object Loading : AuthUiState
    object Authenticated : AuthUiState
    data class Error(val message: String) : AuthUiState
}
