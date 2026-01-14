package com.patidost.app.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Represents a post data structure from the network (Data Transfer Object).
 * Designed to be flexible for various post types.
 */
@Serializable
data class PostDto(
    val postId: String,
    val authorId: String,
    val authorName: String,
    val authorAvatarUrl: String?,
    val text: String,
    val imageUrl: String? = null, // For image posts
    val timestamp: Long,
    val likeCount: Int,
    val relatedPetId: String? = null // For special posts like "Welcome Pet"
)
