package com.patidost.app.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable data object Auth : Screen
    @Serializable data object PetList : Screen
    @Serializable data class PetDetail(val petId: String) : Screen
    @Serializable data object Profile : Screen
    @Serializable data object Cart : Screen
    @Serializable data object AdoptionSuccess : Screen
    @Serializable data object Discover : Screen
}
