package com.patidost.app.domain.model

/**
 * Represents a user's settings in the domain layer.
 */
data class UserSettings(
    val visibilityPreference: String, // e.g., "all", "friends_only"
    val locationEnabled: Boolean,
    val notificationsEnabled: Boolean
)
