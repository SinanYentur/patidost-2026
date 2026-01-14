package com.patidost.app.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a post in the local database.
 * This entity will be the local source of truth for the social feed.
 */
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val postId: String,
    val text: String,
    val imageUrl: String?,
    val timestamp: Long,
    val likeCount: Int,
    val relatedPetId: String?,

    @Embedded(prefix = "author_")
    val author: PostAuthor
)

/**
 * Embedded data class for author details within a PostEntity to keep the schema flat.
 */
data class PostAuthor(
    val id: String,
    val name: String,
    val avatarUrl: String?
)
