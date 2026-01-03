package com.patidost.app.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.patidost.app.domain.model.Pet

/**
 * Pet Room Entity.
 * RVWL: Android Persistence guidelines (Indexed OwnerID).
 */
@Entity(
    tableName = "pets",
    indices = [Index(value = ["ownerId"])]
)
data class PetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val species: String,
    val breed: String,
    val age: Int,
    val imageUrl: String,
    val price: Double,
    val description: String,
    val ownerId: String,
    val createdAt: Long
) {
    fun toDomain(): Pet = Pet(
        id = id,
        name = name,
        species = species,
        breed = breed,
        age = age,
        imageUrl = imageUrl,
        price = price,
        description = description,
        ownerId = ownerId,
        createdAt = createdAt
    )

    companion object {
        fun fromDomain(pet: Pet): PetEntity = PetEntity(
            id = pet.id,
            name = pet.name,
            species = pet.species,
            breed = pet.breed,
            age = pet.age,
            imageUrl = pet.imageUrl,
            price = pet.price,
            description = pet.description,
            ownerId = pet.ownerId,
            createdAt = pet.createdAt
        )
    }
}
