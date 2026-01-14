package com.patidost.app.data.repository

import androidx.paging.PagingData
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePostRepository : PostRepository {

    override fun getSocialFeed(): Flow<PagingData<Post>> {
        return flowOf(PagingData.empty())
    }

    override suspend fun likePost(postId: String) {
        // No-op for fake implementation
    }

    override suspend fun unlikePost(postId: String) {
        // No-op for fake implementation
    }

    override suspend fun createPost(petId: String, text: String, mediaUrl: String?) {
        // No-op for fake implementation
    }

    override suspend fun addComment(postId: String, text: String): Resource<Unit> {
        println("FakePostRepository: addComment called for post $postId with text: $text")
        return Resource.Success(Unit)
    }
}
