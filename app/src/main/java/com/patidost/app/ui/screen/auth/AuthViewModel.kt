package com.patidost.app.ui.screen.auth

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🛡️ Rule 300.1: Process Death Protection.
 * 🛡️ Rule 100: Thin ViewModel.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    // 🛡️ Mühür: Restore email from process death if needed
    val email = savedStateHandle.getStateFlow("email", "")

    fun onEmailChange(newEmail: String) {
        savedStateHandle["email"] = newEmail
    }

    fun onAuthAction(email: String, pass: String, name: String?) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = if (name != null) {
                userRepository.signUp(email, pass, name)
            } else {
                userRepository.signIn(email, pass)
            }
            
            result.onSuccess {
                _uiState.value = AuthUiState.Authenticated
            }.onFailure {
                _uiState.value = AuthUiState.Error(it.message ?: "Authentication failed")
            }
        }
    }
}
