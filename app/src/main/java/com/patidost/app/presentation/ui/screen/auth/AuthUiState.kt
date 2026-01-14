package com.patidost.app.presentation.ui.screen.auth

import com.patidost.app.presentation.ui.util.UiText

data class AuthUiState(
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val name: String = "",
    val nameError: UiText? = null,
    val isLoading: Boolean = false,
    val generalError: UiText? = null
)
