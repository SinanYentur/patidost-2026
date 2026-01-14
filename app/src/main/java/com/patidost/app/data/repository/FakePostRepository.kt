package com.patidost.app.data.repository

import androidx.paging.PagingData
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePostRepository : PostRepository {

    override fun getSocialFeed(): Flow<PagingData<Post>> {
        // Return an empty flow of PagingData to simulate an empty feed.
        // This prevents the app from crashing when no real implementation exists.
        return flowOf(PagingData.empty())
    }

    override suspend fun likePost(postId: String) {
        // No-op for fake implementation
    }

    override suspend fun unlikePost(postId: String) {
        // No-op for fake implementation
    }
}
