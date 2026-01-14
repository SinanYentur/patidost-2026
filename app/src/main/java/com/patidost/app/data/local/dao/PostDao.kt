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

    /**
     * Inserts or updates a list of posts.
     * This is the primary method for populating the cache from the network.
     */
    @Upsert
    suspend fun upsertAll(posts: List<PostEntity>)

    /**
     * Returns a PagingSource that loads posts from the database.
     * This is the single source of truth for the UI.
     */
    @Query("SELECT * FROM posts ORDER BY timestamp DESC")
    fun pagingSource(): PagingSource<Int, PostEntity>

    /**
     * Deletes all posts from the table.
     * Used to clear the cache before a full refresh.
     */
    @Query("DELETE FROM posts")
    suspend fun clearAll()
}
