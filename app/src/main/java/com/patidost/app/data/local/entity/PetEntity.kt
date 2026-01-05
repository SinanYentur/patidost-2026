package com.patidost.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.SyncStatus

/**
 * PetEntity - V10000.24100 Sovereign SSOT.
 * Physical representation of Pet in encrypted Room database.
 * Fully aligned with Android 16 (API 36) and 16KB Page Size mandates.
 */
@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val breed: String,
    val species: String,
    val age: Int,
    val price: Double,
    val ownerId: String,
    val imageUrl: String,
    val videoUrl: String?,
    val description: String,
    val rescueStory: String,
    val personalityTraits: String, // Stored as CSV for SQLite compatibility
    val isAdopted: Boolean,
    val lastUpdated: Long,
    val syncStatus: String
) {
    fun toDomain(): Pet = Pet(
        id = id,
        name = name,
        breed = breed,
        species = species,
        age = age,
        price = price,
        ownerId = ownerId,
        imageUrl = imageUrl,
        videoUrl = videoUrl,
        description = description,
        rescueStory = rescueStory,
        personalityTraits = if (personalityTraits.isEmpty()) emptyList() else personalityTraits.split(","),
        isAdopted = isAdopted,
        lastUpdated = lastUpdated,
        syncStatus = SyncStatus.valueOf(syncStatus)
    )

    companion object {
        fun fromDomain(pet: Pet): PetEntity = PetEntity(
            id = pet.id,
            name = pet.name,
            breed = pet.breed,
            species = pet.species,
            age = pet.age,
            price = pet.price,
            ownerId = pet.ownerId,
            imageUrl = pet.imageUrl,
            videoUrl = pet.videoUrl,
            description = pet.description,
            rescueStory = pet.rescueStory,
            personalityTraits = pet.personalityTraits.joinToString(","),
            isAdopted = pet.isAdopted,
            lastUpdated = pet.lastUpdated,
            syncStatus = pet.syncStatus.name
        )
    }
}
