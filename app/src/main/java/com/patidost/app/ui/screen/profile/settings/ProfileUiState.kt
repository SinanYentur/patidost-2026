package com.patidost.app.ui.screen.profile.settings

import com.patidost.app.domain.model.User

/**
 * ProfileUiState - V2001.10 Hierarchical Restoration.
 */
sealed interface ProfileUiState {
    object Loading : ProfileUiState
    data class Success(val user: User, val isUpdating: Boolean = false) : ProfileUiState
    data class Error(val messageResId: Int) : ProfileUiState
}
