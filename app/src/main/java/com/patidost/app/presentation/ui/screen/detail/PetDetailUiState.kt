package com.patidost.app.presentation.ui.screen.detail

import com.patidost.app.domain.model.Pet
import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the state for the Pet Detail screen.
 * It combines loading, success (pet data), and error states into a single object.
 */
data class PetDetailUiState(
    val isLoading: Boolean = false,
    val pet: Pet? = null,
    val error: UiText? = null
)
