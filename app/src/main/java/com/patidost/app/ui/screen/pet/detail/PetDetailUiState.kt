package com.patidost.app.ui.screen.pet.detail

import com.patidost.app.domain.model.Pet

/**
 * PetDetailUiState - V2000.50 Hierarchical Restoration.
 */
sealed interface PetDetailUiState {
    object Loading : PetDetailUiState
    data class Success(val pet: Pet) : PetDetailUiState
    data class Error(val message: String) : PetDetailUiState
}
