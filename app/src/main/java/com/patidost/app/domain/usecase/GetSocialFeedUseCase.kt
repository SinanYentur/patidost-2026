package com.patidost.app.domain.usecase

import androidx.paging.PagingData
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This use case retrieves the social feed as a stream of PagingData.
 * It acts as a clean entry point for the ViewModel to access the social feed.
 */
class GetSocialFeedUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(): Flow<PagingData<Post>> {
        return postRepository.getSocialFeed()
    }
}
