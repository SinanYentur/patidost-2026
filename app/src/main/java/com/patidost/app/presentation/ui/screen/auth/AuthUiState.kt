package com.patidost.app.presentation.ui.screen.auth

/**
 * Represents the state of the Authentication screens (Login & Register).
 */
data class AuthUiState(
    // Input fields
    val email: String = "",
    val password: String = "",
    val name: String = "", // Only for Register

    // State indicators
    val isLoading: Boolean = false,

    // Error messages for validation
    val emailError: String? = null,
    val passwordError: String? = null,
    val nameError: String? = null, // Only for Register

    // General error from backend or login/register process
    val generalError: String? = null
)
