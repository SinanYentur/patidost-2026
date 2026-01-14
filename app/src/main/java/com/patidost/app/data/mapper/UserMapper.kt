package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.UserDto
import com.patidost.app.domain.model.User

/**
 * Maps a UserDto from the data layer to a User in the domain layer.
 */
fun UserDto.toDomain(): User {
    return User(
        uid = this.id,
        name = this.name,
        email = this.email,
        avatarUrl = this.avatarUrl ?: "",
        patiPoints = this.patiPoints ?: 0,
        status = this.status,
        verificationLevel = this.verificationLevel
    )
}
