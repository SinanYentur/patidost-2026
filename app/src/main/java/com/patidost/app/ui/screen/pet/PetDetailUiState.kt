package com.patidost.app.ui.screen.pet

import androidx.annotation.StringRes
import com.patidost.app.domain.model.Pet

/**
 * Deterministic UI State for Pet Detail screen - 2026 Standard.
 * RVWL: Synchronized with localized resources (Rule 35).
 */
sealed interface PetDetailUiState {
    object Loading : PetDetailUiState
    
    data class Success(
        val pet: Pet,
        val isAdopted: Boolean = false,
        val isAdopting: Boolean = false
    ) : PetDetailUiState

    /**
     * Error state holds a string resource ID.
     * WEB PROOF: Android Localization Best Practices.
     */
    data class Error(@StringRes val messageResId: Int) : PetDetailUiState
}
