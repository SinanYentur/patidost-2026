package com.patidost.app.ui.screen.profile

import com.patidost.app.domain.model.User

sealed interface ProfileUiState {
    object Loading : ProfileUiState
    
    data class Success(
        val user: User,
        val isUpdating: Boolean = false,
        val message: String? = null
    ) : ProfileUiState

    data class Error(val message: String) : ProfileUiState
}
