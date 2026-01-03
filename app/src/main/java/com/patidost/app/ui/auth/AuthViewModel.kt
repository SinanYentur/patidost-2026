package com.patidost.app.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.usecase.auth.DeleteUserUseCase
import com.patidost.app.domain.usecase.auth.GetAuthStateUseCase
import com.patidost.app.domain.usecase.auth.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to manage Authentication State.
 * RVWL: Reactive state mapping per Android Architecture Guide.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    /**
     * Exposes current AuthState to the UI.
     * Maps Domain User to UI AuthState.
     */
    val authState: StateFlow<AuthState> = getAuthStateUseCase()
        .map { user ->
            if (user != null && user.id.isNotEmpty()) {
                AuthState.Authenticated(user)
            } else {
                AuthState.Unauthenticated
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AuthState.Loading
        )

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            deleteUserUseCase()
        }
    }
}
