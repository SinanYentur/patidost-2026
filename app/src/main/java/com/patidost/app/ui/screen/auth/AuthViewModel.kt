package com.patidost.app.ui.screen.auth

import androidx.lifecycle.ViewModel
import com.patidost.app.domain.usecase.auth.SignInUseCase
import com.patidost.app.domain.usecase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 🛡️ GÖREV 0: SIFIR NOKTASI
 * Authentication ekranının durumunu yöneten ViewModel.
 * Sadece UseCase'lere bağımlıdır ve UI katmanının mantığını içerir.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    // UI State (örn: StateFlow<AuthState>) buraya eklenecek.

    // One-shot events (örn: SharedFlow<AuthEvent>) buraya eklenecek.

    // State restoration (SavedStateHandle) buraya entegre edilecek.

    // Fonksiyonlar (örn: fun signIn(email, password)) buraya eklenecek.

}
