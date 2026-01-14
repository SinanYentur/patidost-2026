package com.patidost.app.presentation.ui.screen.explore

import com.patidost.app.domain.repository.PetFilter
import com.patidost.app.presentation.ui.screen.home.FeaturedPet
import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the different states for the Explore screen.
 */
sealed interface ExploreUiState {
    /**
     * The initial state before any search is performed.
     */
    object Idle : ExploreUiState

    /**
     * The screen is currently loading search results.
     */
    object Loading : ExploreUiState

    /**
     * An error occurred during the search.
     */
    data class Error(val message: UiText) : ExploreUiState

    /**
     * The search was successful.
     */
    data class Success(
        val pets: List<FeaturedPet>,
        val query: String,
        val filter: PetFilter
    ) : ExploreUiState {
        val isEmpty: Boolean
            get() = pets.isEmpty()
    }
}
