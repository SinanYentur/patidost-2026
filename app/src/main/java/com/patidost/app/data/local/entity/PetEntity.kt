package com.patidost.app.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.patidost.app.domain.model.PetOwner

/**
 * Represents a pet in the local database.
 * This entity is designed to be the Single Source of Truth for pet data.
 */
@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val age: Int,
    @Embedded val owner: PetOwner
)
