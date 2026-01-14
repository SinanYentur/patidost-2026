package com.patidost.app.presentation.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            when (val result = authRepository.getCurrentUser()) {
                is Resource.Success -> {
                    val user = result.data!!
                    _uiState.value = ProfileUiState.Success(
                        userName = user.name,
                        userEmail = user.email,
                        userAvatarUrl = user.avatarUrl
                    )
                }
                is Resource.Error -> {
                    _uiState.value = ProfileUiState.Error(result.message ?: UiText.DynamicString("Failed to load profile."))
                }
            }
        }
    }

    fun onSignOutClicked() {
        viewModelScope.launch {
            when (authRepository.signOut()) {
                is Resource.Success -> {
                    _navigationEvent.send(NavigationEvent.NavigateToLogin)
                }
                is Resource.Error -> {
                    // Optionally, show a temporary error message to the user
                    // For now, we just log it or ignore it.
                }
            }
        }
    }

    sealed interface NavigationEvent {
        object NavigateToLogin : NavigationEvent
    }
}
