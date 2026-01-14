package com.patidost.app.presentation.ui.screen.home

/**
 * Represents a user in the top givers list.
 */
data class TopGiver(
    val name: String,
    val profileImageUrl: String,
    val score: Int
)

/**
 * Represents the main featured pet on the home screen.
 */
data class FeaturedPet(
    val name: String,
    val breed: String,
    val imageUrl: String,
    val patiId: String,
    val patiScore: Int,
    val owner: PetOwner
)

/**
 * Represents the owner of a pet.
 */
data class PetOwner(
    val name: String,
    val profileImageUrl: String
)
