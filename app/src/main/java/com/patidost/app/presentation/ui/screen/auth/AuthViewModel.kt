package com.patidost.app.presentation.ui.screen.auth // CORRECTED PACKAGE

import androidx.lifecycle.ViewModel
import com.patidost.app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel for authentication state.
 * It determines if a user is logged in or not.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow(authRepository.getCurrentUserId() != null)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    // In later KAPIs, this ViewModel will also handle login/signup events.
}
