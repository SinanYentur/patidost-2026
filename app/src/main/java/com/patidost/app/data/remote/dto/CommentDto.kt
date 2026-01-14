package com.patidost.app.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Represents a comment data structure from the network (Data Transfer Object).
 */
data class CommentDto(
    @SerializedName("comment_id") val commentId: String,
    @SerializedName("post_id") val postId: String, // The post this comment belongs to
    @SerializedName("author_id") val authorId: String,
    @SerializedName("author_name") val authorName: String,
    @SerializedName("author_avatar_url") val authorAvatarUrl: String?,
    @SerializedName("text") val text: String,
    @SerializedName("timestamp") val timestamp: Long
)
