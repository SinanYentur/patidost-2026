package com.patidost.app.domain.repository

import androidx.paging.PagingData
import com.patidost.app.domain.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the repository that handles social feed data.
 */
interface PostRepository {

    /**
     * Returns a flow of PagingData for the social feed.
     * This flow is the single source of truth for paginated post data for the UI.
     */
    fun getSocialFeed(): Flow<PagingData<Post>>
}
