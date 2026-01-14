package com.patidost.app.presentation.ui.screen.auth

import android.util.Patterns
import com.patidost.app.R
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class AuthStateManager @Inject constructor() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, emailError = null, generalError = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null, generalError = null) }
    }

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name, nameError = null, generalError = null) }
    }

    fun validateForLogin(): Boolean {
        val email = _uiState.value.email
        val password = _uiState.value.password

        val isEmailInvalid = email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordInvalid = password.length < 6

        _uiState.update {
            it.copy(
                emailError = if (isEmailInvalid) UiText.StringResource(R.string.error_validation_email_invalid) else null,
                passwordError = if (isPasswordInvalid) UiText.StringResource(R.string.error_validation_password_too_short) else null
            )
        }
        return !isEmailInvalid && !isPasswordInvalid
    }

    fun validateForRegister(): Boolean {
        val name = _uiState.value.name
        val isNameInvalid = name.isBlank()
        val loginValid = validateForLogin()

        _uiState.update {
            it.copy(nameError = if (isNameInvalid) UiText.StringResource(R.string.error_validation_name_empty) else null)
        }
        return !isNameInvalid && loginValid
    }

    fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    fun setGeneralError(error: UiText?) {
        _uiState.update { it.copy(isLoading = false, generalError = error) }
    }
}
