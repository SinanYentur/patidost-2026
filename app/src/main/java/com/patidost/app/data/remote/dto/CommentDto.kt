package com.patidost.app.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Represents a comment data structure from the network (Data Transfer Object).
 */
@Serializable
data class CommentDto(
    val commentId: String,
    val postId: String, // The post this comment belongs to
    val authorId: String,
    val authorName: String,
    val authorAvatarUrl: String?,
    val text: String,
    val timestamp: Long
)
