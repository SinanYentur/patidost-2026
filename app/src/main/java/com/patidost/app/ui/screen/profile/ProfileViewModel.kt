package com.patidost.app.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🛡️ ProfileViewModel - V10000.70027 Sovereign Profile.
 * Rule 421: Restored architectural integrity by injecting both AuthRepository and UserRepository.
 * V2: Corrected logic to use the right repository for each action.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            // User data comes from UserRepository
            authRepository.getCurrentUser().collect { user ->
                _uiState.value = if (user != null) {
                    ProfileUiState.Success(user)
                } else {
                    ProfileUiState.Error("Oturum açılmamış")
                }
            }
        }
    }

    fun updateProfile(user: User) {
        viewModelScope.launch {
            userRepository.updateUserProfile(user)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            // Session management comes from AuthRepository
            authRepository.signOut()
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            // Session management comes from AuthRepository
            authRepository.deleteAccount()
        }
    }

    fun addReminder(reminder: String) {
        // Future implementation for reminders
    }
}
