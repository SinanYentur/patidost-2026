package com.patidost.app.domain.model

/**
 * Represents the owner information embedded within a PetEntity.
 */
data class PetOwner(
    val ownerId: String,
    val ownerName: String,
    val ownerAvatarUrl: String? = null
)
