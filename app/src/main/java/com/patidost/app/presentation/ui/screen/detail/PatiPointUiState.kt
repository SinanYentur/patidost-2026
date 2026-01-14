package com.patidost.app.presentation.ui.screen.detail

import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the state of the Pati Point giving UI.
 */
sealed interface PatiPointUiState {
    object Idle : PatiPointUiState
    object Loading : PatiPointUiState
}

/**
 * Represents one-time events triggered by the PatiPointViewModel.
 */
sealed interface PatiPointEvent {
    data class ShowDonationSuccess(val message: String) : PatiPointEvent
    data class ShowError(val error: UiText) : PatiPointEvent
}
