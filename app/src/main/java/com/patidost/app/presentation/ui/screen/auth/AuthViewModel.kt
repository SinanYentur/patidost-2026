package com.patidost.app.presentation.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.auth.SignInUseCase
import com.patidost.app.domain.usecase.auth.SignInWithGoogleUseCase
import com.patidost.app.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    val stateManager: AuthStateManager // Public to be accessed by the screen
) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<AuthNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged -> stateManager.onEmailChanged(event.email)
            is AuthEvent.PasswordChanged -> stateManager.onPasswordChanged(event.password)
            is AuthEvent.NameChanged -> stateManager.onNameChanged(event.name)
            AuthEvent.Login -> login()
            AuthEvent.Register -> register()
            is AuthEvent.SignInWithGoogle -> signInWithGoogle(event.idToken)
        }
    }

    private fun signInWithGoogle(idToken: String) {
        signInWithGoogleUseCase(idToken).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _navigationEvent.emit(AuthNavigationEvent.NavigateToHome)
                }
                is Resource.Error -> {
                    stateManager.setGeneralError(result.message)
                    stateManager.setLoading(false)
                }
                is Resource.Loading -> {
                    stateManager.setLoading(true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun login() {
        if (!stateManager.validateForLogin()) return

        viewModelScope.launch {
            stateManager.setLoading(true)
            val result = signInUseCase(
                email = stateManager.uiState.value.email,
                password = stateManager.uiState.value.password
            )

            when (result) {
                is Resource.Success -> {
                    _navigationEvent.emit(AuthNavigationEvent.NavigateToHome)
                }
                is Resource.Error -> {
                    stateManager.setGeneralError(result.message)
                }
                is Resource.Loading -> { /* No-op */ }
            }
        }
    }

    private fun register() {
        if (!stateManager.validateForRegister()) return

        viewModelScope.launch {
            stateManager.setLoading(true)
            val result = signUpUseCase(
                name = stateManager.uiState.value.name,
                email = stateManager.uiState.value.email,
                password = stateManager.uiState.value.password
            )

            when (result) {
                is Resource.Success -> {
                    _navigationEvent.emit(AuthNavigationEvent.NavigateToHome)
                }
                is Resource.Error -> {
                    stateManager.setGeneralError(result.message)
                }
                is Resource.Loading -> { /* No-op */ }
            }
        }
    }
}

sealed interface AuthEvent {
    data class EmailChanged(val email: String) : AuthEvent
    data class PasswordChanged(val password: String) : AuthEvent
    data class NameChanged(val name: String) : AuthEvent
    data class SignInWithGoogle(val idToken: String) : AuthEvent
    object Login : AuthEvent
    object Register : AuthEvent
}

sealed interface AuthNavigationEvent {
    object NavigateToHome : AuthNavigationEvent
}
