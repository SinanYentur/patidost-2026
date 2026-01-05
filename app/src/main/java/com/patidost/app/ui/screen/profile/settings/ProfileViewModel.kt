package com.patidost.app.ui.screen.profile.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.R
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Profile ViewModel - V2001.15 Hierarchical Migration.
 * RVWL: Ported from legacy ProfileViewModel with sub-feature isolation.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isUpdating = MutableStateFlow(false)
    private val _errorResId = MutableStateFlow<Int?>(null)

    val uiState: StateFlow<ProfileUiState> = userRepository.getCurrentUser()
        .flatMapLatest { user ->
            if (user == null || user.id.isEmpty()) {
                flowOf(ProfileUiState.Error(R.string.error_session_not_found))
            } else {
                combine(
                    userRepository.getUserProfile(user.id),
                    _isUpdating,
                    _errorResId
                ) { profile, isUpdating, errorId ->
                    when {
                        errorId != null -> ProfileUiState.Error(errorId)
                        profile == null -> ProfileUiState.Loading
                        else -> ProfileUiState.Success(user = profile, isUpdating = isUpdating)
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProfileUiState.Loading
        )

    fun updateProfile(user: User) {
        viewModelScope.launch {
            _isUpdating.update { true }
            _errorResId.update { null }
            userRepository.updateUserProfile(user).onFailure {
                _errorResId.update { R.string.error_profile_update }
            }
            _isUpdating.update { false }
        }
    }
}
