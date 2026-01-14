package com.patidost.app.presentation.ui.screen.add_pet

import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the state of the Add Pet form.
 */
data class AddPetUiState(
    val name: String = "",
    val breed: String = "",
    val age: String = "",
    val imageUrl: String = "",
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val error: UiText? = null
)
