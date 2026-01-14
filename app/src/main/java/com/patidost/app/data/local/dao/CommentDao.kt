package com.patidost.app.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.patidost.app.data.local.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the comments table.
 */
@Dao
interface CommentDao {

    @Upsert
    suspend fun upsertAll(comments: List<CommentEntity>)

    /**
     * Retrieves all comments for a specific post, ordered by timestamp.
     */
    @Query("SELECT * FROM comments WHERE postId = :postId ORDER BY timestamp DESC")
    fun getCommentsForPost(postId: String): Flow<List<CommentEntity>>

    @Query("DELETE FROM comments WHERE postId = :postId")
    suspend fun deleteCommentsForPost(postId: String)
}
