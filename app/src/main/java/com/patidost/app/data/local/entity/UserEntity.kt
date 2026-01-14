package com.patidost.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user in the local database.
 * This must be kept in sync with the User domain model.
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val uid: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val patiPoints: Int = 0,
    val status: String, // Added to match domain model
    val verificationLevel: Int // Added to match domain model
)
