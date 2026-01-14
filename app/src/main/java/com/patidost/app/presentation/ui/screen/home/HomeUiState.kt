package com.patidost.app.presentation.ui.screen.home // CORRECTED PACKAGE

import com.patidost.app.domain.model.Pet

/**
 * Represents the state for the Home screen.
 * It holds all the necessary data to render the home UI.
 */
data class HomeUiState(
    val isLoading: Boolean = true,
    val patiPuan: Int = 0,
    val petList: List<Pet> = emptyList(),
    val error: String? = null
)
