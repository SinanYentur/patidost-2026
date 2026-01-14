package com.patidost.app.domain.model

/**
 * Represents a user's public profile information in the domain layer.
 */
data class UserProfile(
    val userId: String,
    val name: String,
    val bio: String,
    val photoUrl: String
)
