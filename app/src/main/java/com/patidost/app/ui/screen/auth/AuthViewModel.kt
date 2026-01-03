package com.patidost.app.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.R
import com.patidost.app.domain.usecase.auth.GetAuthStateUseCase
import com.patidost.app.domain.usecase.auth.SignInUseCase
import com.patidost.app.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Authentication - 2026 Standard.
 * RVWL: Synchronized with com.patidost.app package identity and atomic use cases.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    getAuthStateUseCase: GetAuthStateUseCase,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _isLoginMode = MutableStateFlow(true)
    private val _isLoading = MutableStateFlow(false)
    private val _errorResId = MutableStateFlow<Int?>(null)

    /**
     * UI State stream for the Auth Screen.
     */
    val uiState: StateFlow<AuthUiState> = combine(
        getAuthStateUseCase(),
        _isLoading,
        _errorResId,
        _isLoginMode
    ) { user, isLoading, errorId, isLoginMode ->
        when {
            isLoading -> AuthUiState.Loading
            errorId != null -> AuthUiState.Error(errorId, isLoginMode)
            user != null && user.id.isNotEmpty() -> AuthUiState.Authenticated(user)
            else -> AuthUiState.Unauthenticated
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthUiState.Initial
    )

    fun toggleMode() {
        _isLoginMode.update { !it }
        _errorResId.update { null }
    }

    /**
     * Entry point for Auth actions.
     * WEB PROOF: Firebase Auth Best Practices (runCatching & State Mapping).
     */
    fun onAuthAction(email: String, pass: String, name: String = "") {
        viewModelScope.launch {
            _isLoading.value = true
            _errorResId.value = null
            
            val result = if (_isLoginMode.value) {
                signInUseCase(email, pass)
            } else {
                signUpUseCase(email, pass, name)
            }

            result.onFailure {
                _errorResId.value = R.string.error_auth_failed
            }
            
            _isLoading.value = false
        }
    }
}
