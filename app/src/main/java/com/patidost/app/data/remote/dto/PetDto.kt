package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

/**
 * Data Transfer Object for Pet.
 * Matches the Firestore document structure exactly.
 */
data class PetDto(
    @get:PropertyName("id") @set:PropertyName("id") var id: String = "",
    @get:PropertyName("ownerId") @set:PropertyName("ownerId") var ownerId: String = "",
    @get:PropertyName("name") @set:PropertyName("name") var name: String = "",
    @get:PropertyName("age") @set:PropertyName("age") var age: Int = 0,
    @get:PropertyName("breed") @set:PropertyName("breed") var breed: String = "",
    @get:PropertyName("gender") @set:PropertyName("gender") var gender: String = "",
    @get:PropertyName("description") @set:PropertyName("description") var description: String = "",
    @get:PropertyName("isVisibleInSwipe") @set:PropertyName("isVisibleInSwipe") var isVisibleInSwipe: Boolean = true,
    @get:PropertyName("state") @set:PropertyName("state") var state: String = "active",
    @get:PropertyName("createdAt") @set:PropertyName("createdAt") @ServerTimestamp var createdAt: Date? = null
)
