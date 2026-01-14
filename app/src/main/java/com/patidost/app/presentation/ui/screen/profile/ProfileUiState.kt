package com.patidost.app.presentation.ui.screen.profile

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.User
import com.patidost.app.domain.model.economy.Subscription
import com.patidost.app.domain.model.economy.Wallet
import com.patidost.app.presentation.ui.util.UiText

/**
 * Represents the state for the Profile screen.
 */
data class ProfileUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val pets: List<Pet> = emptyList(),
    val wallet: Wallet? = null,
    val subscription: Subscription? = null,
    val error: UiText? = null
)
