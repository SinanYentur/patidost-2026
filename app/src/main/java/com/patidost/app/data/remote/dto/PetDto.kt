package com.patidost.app.data.remote.dto

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

/**
 * Represents a Pet data structure from Firestore (Data Transfer Object).
 * This matches the schema for documents in the /pets collection.
 */
data class PetDto(
    val ownerId: String = "",
    val name: String = "",
    val species: String = "",
    val breed: String = "",
    val gender: String = "",
    val birthDate: Date? = null,
    val photoUrl: String? = null,
    val location: LocationDto? = null,
    val totalPati: Int = 0,
    val isUpForAdoption: Boolean = false,
    @ServerTimestamp
    val createdAt: Date? = null
) {
    // Firestore document ID, to be populated manually after fetching.
    lateinit var id: String
}

/**
 * Represents the location object nested within a PetDto.
 */
data class LocationDto(
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val city: String = ""
)
