package com.patidost.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val name: String,
    val profileImageUrl: String? = null,
    val updatedAt: Long = System.currentTimeMillis()
)

fun UserEntity.toDomain() = com.patidost.app.domain.model.User(
    id = id,
    email = email,
    name = name
)

fun com.patidost.app.domain.model.User.toEntity() = UserEntity(
    id = id,
    email = email,
    name = name
)
