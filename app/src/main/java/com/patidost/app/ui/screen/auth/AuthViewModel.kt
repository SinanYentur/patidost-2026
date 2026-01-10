package com.patidost.app.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.model.valueobject.EmailVO
import com.patidost.app.domain.model.valueobject.PasswordVO
import com.patidost.app.domain.usecase.auth.SignInUseCase
import com.patidost.app.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🛡️ AuthViewModel - Sovereign Identity Gate.
 * V5: Restored architectural integrity by using specific UseCases instead of a general repository.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    fun signUp(email: String, pass: String, name: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            // VOs enforce validation at the boundary
            val emailVO = EmailVO(email)
            val passwordVO = PasswordVO(pass)
            signUpUseCase(emailVO, passwordVO, name)
                .onSuccess { user ->
                    _authState.value = AuthState.Success(user.name)
                }
                .onFailure { error ->
                    _authState.value = AuthState.Error(error.message)
                }
        }
    }

    fun signIn(email: String, pass: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val emailVO = EmailVO(email)
            val passwordVO = PasswordVO(pass)
            signInUseCase(emailVO, passwordVO)
                .onSuccess { user ->
                    _authState.value = AuthState.Success(user.name)
                }
                .onFailure { error ->
                    _authState.value = AuthState.Error(error.message)
                }
        }
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userName: String) : AuthState()
    data class Error(val message: String) : AuthState()
}
