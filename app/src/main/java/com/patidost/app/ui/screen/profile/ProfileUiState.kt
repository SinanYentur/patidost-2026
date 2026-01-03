package com.patidost.app.ui.screen.profile

import androidx.annotation.StringRes
import com.patidost.app.domain.model.User

/**
 * UI State for Profile Settings - 2026 Standard.
 */
sealed interface ProfileUiState {
    object Loading : ProfileUiState
    
    data class Success(
        val user: User,
        val isUpdating: Boolean = false,
        @StringRes val messageResId: Int? = null
    ) : ProfileUiState

    data class Error(@StringRes val messageResId: Int) : ProfileUiState
}
