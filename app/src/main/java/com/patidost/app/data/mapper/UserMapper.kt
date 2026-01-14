package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.UserDto
import com.patidost.app.domain.model.User
import java.util.Date

/**
 * Maps User DTOs to Domain models and vice versa.
 * Handles nullability to ensure the domain layer is safe.
 */
fun UserDto.toDomain(): User {
    return User(
        id = id,
        email = email,
        phone = phone,
        gender = gender,
        status = status,
        verificationLevel = verificationLevel,
        createdAt = createdAt ?: Date()
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        phone = phone,
        gender = gender,
        status = status,
        verificationLevel = verificationLevel,
        createdAt = createdAt
    )
}
