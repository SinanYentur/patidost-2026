package com.patidost.app.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            userRepository.getCurrentUser().collect { user ->
                _uiState.value = if (user != null) {
                    ProfileUiState.Success(user)
                } else {
                    ProfileUiState.Error("User not logged in")
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
            userRepository.signOut()
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            userRepository.deleteAccount()
        }
    }

    fun addReminder(reminder: String) {
        // Implementation for reminders
    }
}
