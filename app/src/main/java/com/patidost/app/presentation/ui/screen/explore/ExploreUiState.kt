package com.patidost.app.presentation.ui.screen.explore

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.TopGiver
import com.patidost.app.presentation.ui.util.UiText

data class ExploreUiState(
    val isLoading: Boolean = false,
    val pets: List<Pet> = emptyList(),
    val topGivers: List<TopGiver> = emptyList(),
    val error: UiText? = null, // Reverted back to UiText for architectural consistency
    val searchQuery: String = ""
)
