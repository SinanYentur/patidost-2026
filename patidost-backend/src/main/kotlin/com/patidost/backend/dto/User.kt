package com.patidost.backend.dto

import kotlinx.serialization.Serializable

/**
 * Represents a user profile, used for elements like "Top Pet Owners".
 * Backend model based on the visual evidence from the Home screen.
 */
@Serializable
data class User(
    val id: String,
    val name: String,
    val profileImageUrl: String
)
