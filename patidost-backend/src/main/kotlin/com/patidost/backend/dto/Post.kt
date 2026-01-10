package com.patidost.backend.dto

import kotlinx.serialization.Serializable

/**
 * Represents a single post in the home feed.
 * Backend model based on the visual evidence from the Home screen.
 */
@Serializable
data class Post(
    val id: String,
    val author: User,
    val content: String,
    val timestamp: String, // e.g., "10 min ago"
    val imageUrl: String? = null,
    val likes: Int,
    val comments: Int
)
