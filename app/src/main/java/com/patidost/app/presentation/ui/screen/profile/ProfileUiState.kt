package com.patidost.app.presentation.ui.screen.profile

import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the different states for the Profile screen.
 */
sealed interface ProfileUiState {
    /**
     * The screen is currently loading user data.
     */
    object Loading : ProfileUiState

    /**
     * An error occurred while fetching data.
     */
    data class Error(val message: UiText) : ProfileUiState

    /**
     * The user data was fetched successfully.
     */
    data class Success(
        val userName: String,
        val userEmail: String,
        val userAvatarUrl: String
    ) : ProfileUiState
}
