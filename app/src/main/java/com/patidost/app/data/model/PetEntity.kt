package com.patidost.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val breed: String,
    val species: String = "", // Eksik olan alanlar eklendi
    val age: Int,
    val price: Double = 0.0,
    val ownerId: String = "",
    val imageUrl: String,
    val description: String,
    val isAdopted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
