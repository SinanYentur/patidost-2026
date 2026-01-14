package com.patidost.app.domain.model

/**
 * Represents a comment in the domain layer.
 * This is the clean model that the UI layer will interact with.
 */
data class Comment(
    val commentId: String,
    val postId: String,
    val author: CommentAuthor,
    val text: String,
    val timestamp: Long
)

/**
 * Represents the author of a comment in the domain layer.
 */
data class CommentAuthor(
    val id: String,
    val name: String,
    val avatarUrl: String?
)
