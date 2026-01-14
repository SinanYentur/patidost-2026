package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.UserProfileDto
import com.patidost.app.domain.model.UserProfile

/**
 * Maps UserProfile DTOs to Domain models and vice versa.
 */
fun UserProfileDto.toDomain(): UserProfile {
    return UserProfile(
        userId = userId,
        name = name,
        bio = bio,
        photoUrl = photoUrl
    )
}

fun UserProfile.toDto(): UserProfileDto {
    return UserProfileDto(
        userId = userId,
        name = name,
        bio = bio,
        photoUrl = photoUrl
    )
}
