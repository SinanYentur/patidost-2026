package com.patidost.app.presentation.ui.screen.detail

data class PetDetailUiState(
    val imageUrl: String = "",
    val description: String = "",
    val age: Int = 0,
    val breed: String = "",
    val healthStatus: String = "",
    val isFavorite: Boolean = false,
    val isLoading: Boolean = true,
    val error: String? = null
)
