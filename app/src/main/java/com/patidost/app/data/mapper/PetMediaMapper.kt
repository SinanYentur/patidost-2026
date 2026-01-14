package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.PetMediaDto
import com.patidost.app.domain.model.PetMedia
import java.util.Date

/**
 * Maps PetMedia DTOs to Domain models and vice versa.
 */
fun PetMediaDto.toDomain(): PetMedia {
    return PetMedia(
        id = id,
        petId = petId,
        url = url,
        thumbnailUrl = thumbnailUrl,
        createdAt = createdAt ?: Date()
    )
}

fun PetMedia.toDto(): PetMediaDto {
    return PetMediaDto(
        id = id,
        petId = petId,
        url = url,
        thumbnailUrl = thumbnailUrl,
        createdAt = createdAt
    )
}
