package com.patidost.app.domain.model

/**
 * Represents a post in the domain layer, clean from any data or UI specific annotations.
 * This is the model the UI will interact with.
 */
data class Post(
    val postId: String,
    val author: PostAuthor,
    val text: String,
    val imageUrl: String?,
    val timestamp: Long,
    val likeCount: Int,
    val relatedPetId: String?
)

data class PostAuthor(
    val id: String,
    val name: String,
    val avatarUrl: String?
)
