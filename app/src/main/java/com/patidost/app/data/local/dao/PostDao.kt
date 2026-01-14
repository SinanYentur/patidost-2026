package com.patidost.app.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.patidost.app.data.local.entity.PostEntity

/**
 * Data Access Object for the posts table.
 */
@Dao
interface PostDao {

    @Upsert
    suspend fun upsertAll(posts: List<PostEntity>)

    @Query("SELECT * FROM posts ORDER BY timestamp DESC")
    fun pagingSource(): PagingSource<Int, PostEntity>

    @Query("DELETE FROM posts")
    suspend fun clearAll()

    /**
     * Atomically updates the like status and count of a post.
     * This is the core of the optimistic update mechanism in the local database.
     */
    @Query("UPDATE posts SET isLiked = :isLiked, likeCount = likeCount + :likeCountChange WHERE postId = :postId")
    suspend fun updateLikeStatus(postId: String, isLiked: Boolean, likeCountChange: Int)
}
