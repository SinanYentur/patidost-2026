package com.patidost.app.presentation.ui.screen.detail

import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the state for giving Pati Points.
 */
data class PatiPointUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: UiText? = null
)
