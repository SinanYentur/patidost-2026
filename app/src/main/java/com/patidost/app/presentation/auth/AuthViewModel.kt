package com.patidost.app.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.usecase.auth.SignInUseCase
import com.patidost.app.domain.usecase.auth.SignUpUseCase
import com.patidost.app.domain.util.DomainResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<AuthEffect>()
    val effect = _effect.receiveAsFlow()

    fun onSignIn(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            when (val result = signInUseCase(email, pass)) {
                is DomainResult.Success -> {
                    _uiState.value = AuthUiState.Authenticated(result.data)
                    _effect.send(AuthEffect.NavigateToHome)
                }
                is DomainResult.Error -> {
                    _uiState.value = AuthUiState.Error(result.error)
                    _effect.send(AuthEffect.ShowToast("Giriş Başarısız"))
                }
            }
        }
    }
}
