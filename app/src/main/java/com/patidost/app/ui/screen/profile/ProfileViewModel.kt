package com.patidost.app.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.R
import com.patidost.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Profile Settings - 2026 Standard.
 * RVWL: Atomic profile updates and Photo Picker state management.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isUpdating = MutableStateFlow(false)
    private val _messageResId = MutableStateFlow<Int?>(null)

    /**
     * Re-active UI State for the Profile Screen.
     */
    val uiState: StateFlow<ProfileUiState> = combine(
        userRepository.getCurrentUser(),
        _isUpdating,
        _messageResId
    ) { user, isUpdating, messageId ->
        when {
            user == null -> ProfileUiState.Error(R.string.error_session_not_found)
            else -> ProfileUiState.Success(
                user = user,
                isUpdating = isUpdating,
                messageResId = messageId
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProfileUiState.Loading
    )

    /**
     * Updates profile metadata using atomic Firestore set(merge=true).
     */
    fun updateProfile(name: String, bio: String, photoUrl: String) {
        viewModelScope.launch {
            val currentState = uiState.value
            if (currentState !is ProfileUiState.Success) return@launch

            _isUpdating.value = true
            _messageResId.value = null

            userRepository.updateProfile(
                userId = currentState.user.id,
                name = name,
                bio = bio,
                photoUrl = photoUrl
            ).onSuccess {
                _messageResId.value = R.string.success_profile_update
            }.onFailure {
                _messageResId.value = R.string.error_profile_update
            }

            _isUpdating.value = false
        }
    }
}
