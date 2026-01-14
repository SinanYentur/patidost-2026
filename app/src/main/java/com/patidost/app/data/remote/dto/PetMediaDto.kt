package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

/**
 * Data Transfer Object for PetMedia.
 * Matches the Firestore document structure exactly.
 */
data class PetMediaDto(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("petId") @set:PropertyName("petId") var petId: String = "",
    @get:PropertyName("url") @set:PropertyName("url") var url: String = "",
    @get:PropertyName("thumbnailUrl") @set:PropertyName("thumbnailUrl") var thumbnailUrl: String = "",
    @get:PropertyName("createdAt") @set:PropertyName("createdAt") @ServerTimestamp var createdAt: Date? = null
)
