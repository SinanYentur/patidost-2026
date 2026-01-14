package com.patidost.app.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Represents a comment in the local database, linked to a specific post.
 */
@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = PostEntity::class,
            parentColumns = ["postId"],
            childColumns = ["postId"],
            onDelete = ForeignKey.CASCADE // If a post is deleted, all its comments are deleted.
        )
    ]
)
data class CommentEntity(
    @PrimaryKey
    val commentId: String,
    val postId: String, // Foreign key to PostEntity
    val text: String,
    val timestamp: Long,

    @Embedded(prefix = "author_")
    val author: CommentAuthor
)

/**
 * Embedded data class for author details within a CommentEntity.
 */
data class CommentAuthor(
    val id: String,
    val name: String,
    val avatarUrl: String?
)
