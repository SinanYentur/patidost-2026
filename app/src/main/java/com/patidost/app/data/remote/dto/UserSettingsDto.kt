package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName

/**
 * Data Transfer Object for UserSettings.
 * Matches the Firestore document structure exactly.
 */
data class UserSettingsDto(
    @get:PropertyName("visibilityPreference") @set:PropertyName("visibilityPreference") var visibilityPreference: String = "all",
    @get:PropertyName("locationEnabled") @set:PropertyName("locationEnabled") var locationEnabled: Boolean = true,
    @get:PropertyName("notificationsEnabled") @set:PropertyName("notificationsEnabled") var notificationsEnabled: Boolean = true
)
