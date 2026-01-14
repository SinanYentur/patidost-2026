package com.patidost.app.domain.model

/**
 * Represents the core User entity in the domain layer.
 * This class holds all the necessary information about a user.
 */
data class User(
    val uid: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val patiPoints: Int,
    val status: String, // e.g., "active", "suspended"
    val verificationLevel: Int // e.g., 1 for email, 2 for phone
)
