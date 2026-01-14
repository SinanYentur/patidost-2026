package com.patidost.app.domain.repository

import androidx.paging.PagingData
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the repository that handles social feed data.
 */
interface PostRepository {

    /**
     * Returns a flow of PagingData for the social feed.
     */
    fun getSocialFeed(): Flow<PagingData<Post>>

    /**
     * Likes a post.
     */
    suspend fun likePost(postId: String)

    /**
     * Unlikes a post.
     */
    suspend fun unlikePost(postId: String)

    /**
     * Creates a new post.
     */
    suspend fun createPost(
        petId: String,
        text: String,
        mediaUrl: String?
    )

    /**
     * Adds a comment to a post.
     */
    suspend fun addComment(postId: String, text: String): Resource<Unit>
}
