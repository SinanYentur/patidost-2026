package com.patidost.app.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.usecase.auth.GetAuthStateUseCase
import com.patidost.app.domain.usecase.auth.SignInUseCase
import com.patidost.app.domain.usecase.auth.SignUpUseCase
import com.patidost.app.domain.usecase.auth.SignInWithGoogleUseCase
import com.patidost.app.domain.usecase.auth.SignInWithFacebookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import timber.log.Timber

/**
 * Login ViewModel - V10000.97000 Sovereign MVI.
 * Rule 100: Clean separation of UI state and Logic.
 * FIXED: Changed initialValue to Loading to prevent black-screen flicker on start.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithFacebookUseCase: SignInWithFacebookUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<LoginUiState> = combine(
        getAuthStateUseCase(),
        _isLoading,
        _errorMessage
    ) { user, isLoading, errorMsg ->
        when {
            isLoading -> LoginUiState.Loading
            errorMsg != null -> LoginUiState.ErrorMsg(errorMsg)
            user != null -> LoginUiState.Authenticated(user)
            else -> LoginUiState.Unauthenticated
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LoginUiState.Loading // 🛡️ Mühür: Siyah ekranı engellemek için başlangıç Loading.
    )

    fun onSignIn(email: String, pass: String) {
        performAuthAction("SignIn") { signInUseCase(email, pass) }
    }

    fun onSignUp(email: String, pass: String, name: String) {
        performAuthAction("SignUp") { signUpUseCase(email, pass, name) }
    }

    fun onGoogleSignIn() {
        performAuthAction("GoogleSignIn") { signInWithGoogleUseCase() }
    }

    fun onFacebookSignIn() {
        performAuthAction("FacebookSignIn") { signInWithFacebookUseCase() }
    }

    private fun performAuthAction(tag: String, action: suspend () -> Result<*>) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            Timber.tag("AuthAction").d("Executing $tag")
            
            action().onSuccess {
                Timber.tag("AuthAction").d("$tag Success")
                _isLoading.value = false
            }.onFailure { exception ->
                Timber.tag("AuthAction").e(exception, "$tag Failed")
                _errorMessage.value = exception.message ?: "Bilinmeyen bir hata oluştu"
                _isLoading.value = false
            }
        }
    }
}
