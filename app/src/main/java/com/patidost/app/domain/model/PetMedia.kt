package com.patidost.app.domain.model

import java.util.Date

/**
 * Represents a pet's media item (photo/video) in the domain layer.
 */
data class PetMedia(
    val id: String,
    val petId: String,
    val url: String,
    val thumbnailUrl: String,
    val createdAt: Date
)
