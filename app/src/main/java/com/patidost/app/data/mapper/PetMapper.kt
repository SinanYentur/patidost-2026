package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.domain.model.Pet
import java.util.Date

/**
 * Maps Pet DTOs to Domain models and vice versa.
 */
fun PetDto.toDomain(): Pet {
    return Pet(
        id = id,
        ownerId = ownerId,
        name = name,
        age = age,
        breed = breed,
        gender = gender,
        description = description,
        isVisibleInSwipe = isVisibleInSwipe,
        state = state,
        createdAt = createdAt ?: Date()
    )
}

fun Pet.toDto(): PetDto {
    return PetDto(
        id = id,
        ownerId = ownerId,
        name = name,
        age = age,
        breed = breed,
        gender = gender,
        description = description,
        isVisibleInSwipe = isVisibleInSwipe,
        state = state,
        createdAt = createdAt
    )
}
