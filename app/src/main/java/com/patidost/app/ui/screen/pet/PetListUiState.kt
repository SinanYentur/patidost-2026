package com.patidost.app.ui.screen.pet

import androidx.annotation.StringRes
import com.patidost.app.domain.model.Pet

/**
 * Deterministic UI State for Pet List screen.
 * RVWL: Synchronized with string resources for localization (Rule 35).
 */
sealed interface PetListUiState {
    object Loading : PetListUiState
    
    data class Success(
        val pets: List<Pet>,
        val isRefreshing: Boolean = false
    ) : PetListUiState

    /**
     * Error state holds a string resource ID.
     * WEB PROOF: Android Localization Best Practices.
     */
    data class Error(@StringRes val messageResId: Int) : PetListUiState
}
