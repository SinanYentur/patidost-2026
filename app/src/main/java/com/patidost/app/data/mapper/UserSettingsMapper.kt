package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.UserSettingsDto
import com.patidost.app.domain.model.UserSettings

/**
 * Maps UserSettings DTOs to Domain models and vice versa.
 */
fun UserSettingsDto.toDomain(): UserSettings {
    return UserSettings(
        visibilityPreference = visibilityPreference,
        locationEnabled = locationEnabled,
        notificationsEnabled = notificationsEnabled
    )
}

fun UserSettings.toDto(): UserSettingsDto {
    return UserSettingsDto(
        visibilityPreference = visibilityPreference,
        locationEnabled = locationEnabled,
        notificationsEnabled = notificationsEnabled
    )
}
