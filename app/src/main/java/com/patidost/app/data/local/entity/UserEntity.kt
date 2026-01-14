package com.patidost.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user in the local database.
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val uid: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val patiPoints: Int
)
