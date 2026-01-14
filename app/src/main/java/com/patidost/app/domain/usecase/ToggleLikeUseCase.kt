package com.patidost.app.domain.usecase

import com.patidost.app.domain.repository.PostRepository
import javax.inject.Inject

/**
 * A use case for toggling the like status of a post.
 * This encapsulates the business logic for liking and unliking.
 */
class ToggleLikeUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(postId: String, isCurrentlyLiked: Boolean) {
        // The actual logic of how to handle the like/unlike (e.g., API calls)
        // will be in the repository. This use case is the entry point for that logic.
        if (isCurrentlyLiked) {
            postRepository.unlikePost(postId)
        } else {
            postRepository.likePost(postId)
        }
    }
}
