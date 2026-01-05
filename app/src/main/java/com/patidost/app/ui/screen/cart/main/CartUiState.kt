package com.patidost.app.ui.screen.cart.main

import androidx.compose.runtime.Immutable
import com.patidost.app.domain.model.Pet
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * CartUiState - V10000.17000 Financial Singularity.
 * Rule 105: Zero Void in financial layers.
 */
@Immutable
data class CartUiState(
    val isLoading: Boolean = false,
    val cartItems: ImmutableList<Pet> = persistentListOf(),
    val totalAmount: Double = 0.0,
    val userPatiPoints: Int = 0,
    val isProcessing: Boolean = false,
    val error: String? = null
)
