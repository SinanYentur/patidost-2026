package com.patidost.app.data.mapper

import com.patidost.app.data.model.PetEntity
import com.patidost.app.domain.model.Pet

/**
 * 🛡️ PetMapper - V10000.70014 Sovereign Realignment.
 * Rule 300.2: SSOT Sync with data.model path.
 */
fun PetEntity.toDomain(): Pet {
    return Pet(
        id = id,
        name = name,
        breed = breed,
        imageUrl = imageUrl,
        description = description
    )
}

fun Pet.toEntity(): PetEntity {
    return PetEntity(
        id = id,
        name = name,
        breed = breed,
        imageUrl = imageUrl,
        description = description,
        age = 0 // Default for compatibility
    )
}
