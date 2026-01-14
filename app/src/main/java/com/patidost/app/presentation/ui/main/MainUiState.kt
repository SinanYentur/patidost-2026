package com.patidost.app.presentation.ui.main

/**
 * Represents the UI state for the main activity, primarily for determining the start destination.
 */
data class MainUiState(
    val isLoading: Boolean = true,
    val startDestination: String? = null
)
