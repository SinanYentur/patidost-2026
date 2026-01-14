package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Comment
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the repository that handles comment data.
 */
interface CommentRepository {

    /**
     * Returns a flow of comments for a specific post.
     * It follows the single source of truth pattern.
     */
    fun getCommentsForPost(postId: String): Flow<Resource<List<Comment>>>

    /**
     * Adds a new comment to a post.
     */
    suspend fun addComment(postId: String, text: String): Resource<Unit>
}
