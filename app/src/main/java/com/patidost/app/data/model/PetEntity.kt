package com.patidost.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 🛡️ PetEntity - V10000.70013 Sovereign Persistence Model.
 * Rule 300.2: Schema Safety & SSOT.
 * Note: Integrated with species, age, and price for Global Launch.
 */
@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val breed: String,
    val species: String = "",
    val age: Int = 0,
    val price: Double = 0.0,
    val ownerId: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val isAdopted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
