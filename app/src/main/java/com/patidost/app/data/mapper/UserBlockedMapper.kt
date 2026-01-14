package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.UserBlockedDto
import com.patidost.app.domain.model.UserBlocked
import java.util.Date

fun UserBlockedDto.toDomain(): UserBlocked {
    return UserBlocked(
        id = id,
        userId = userId,
        blockedUserId = blockedUserId,
        createdAt = createdAt ?: Date()
    )
}

fun UserBlocked.toDto(): UserBlockedDto {
    return UserBlockedDto(
        id = id,
        userId = userId,
        blockedUserId = blockedUserId,
        createdAt = createdAt
    )
}
