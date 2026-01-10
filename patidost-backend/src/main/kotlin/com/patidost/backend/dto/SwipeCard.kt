package com.patidost.backend.dto

import kotlinx.serialization.Serializable

/**
 * 🛡️ SwipeCard - Data capsule for a single profile in the swipe feed.
 * Represents the combined information of a pet and its owner for the main swipe screen.
 */
@Serializable
data class SwipeCard(
    val petId: String,
    val petName: String,
    val petAge: Int,
    val petImageUrl: String,
    val ownerId: String,
    val ownerName: String,
    val ownerProfileImageUrl: String,
    val distance: Int // Placeholder for distance in km
)
