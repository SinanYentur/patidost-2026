package com.patidost.app.ui.screen.home

import com.patidost.app.domain.model.Pet

sealed class HomeUiState {
    object Idle : HomeUiState()
    object Loading : HomeUiState()
    object Empty : HomeUiState()
    data class Content(val pets: List<Pet>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
