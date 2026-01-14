package com.patidost.app.presentation.ui.screen.home

import com.patidost.app.domain.model.Pet
import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the different states for the Home screen.
 */
sealed interface HomeUiState {
    /**
     * The screen is currently loading initial data.
     */
    data object Loading : HomeUiState

    /**
     * The screen has successfully loaded the data.
     */
    data class Success(
        val featuredPets: List<Pet>,
        val topGivers: List<TopGiver> = emptyList(), // Keep this for now
    ) : HomeUiState

    /**
     * There is no data to display.
     */
    data object Empty : HomeUiState

    /**
     * An error occurred while loading the data.
     */
    data class Error(val message: UiText?) : HomeUiState
}
