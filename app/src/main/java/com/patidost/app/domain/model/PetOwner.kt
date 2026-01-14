package com.patidost.app.domain.model

/**
 * Represents the owner information embedded within a PetEntity.
 * This is a simple data class suitable for Room's @Embedded feature.
 */
data class PetOwner(
    val ownerId: String,
    val ownerName: String,
    val ownerAvatarUrl: String? = null
)
