package com.patidost.app.presentation.pet

import com.patidost.app.domain.model.Pet

/**
 * 🛡️ Rule 100: MVI-Lite UI State.
 * Exhaustive state definition for the Home screen.
 */
sealed interface HomeUiState {
    object Idle : HomeUiState
    object Loading : HomeUiState
    object Empty : HomeUiState
    data class Content(val pets: List<Pet>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
