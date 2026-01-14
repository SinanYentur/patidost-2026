package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.manager.RateLimiter
import com.patidost.app.domain.model.LimitableAction
import com.patidost.app.domain.repository.PostRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val postRepository: PostRepository, // Corrected repository
    private val rateLimiter: RateLimiter
) {
    suspend operator fun invoke(postId: String, text: String): Resource<Unit> {
        if (!rateLimiter.canPerform(LimitableAction.POST_COMMENT)) {
            val remaining = rateLimiter.getRemainingCooldown(LimitableAction.POST_COMMENT) / 1000
            return Resource.Error(UiText.DynamicString("Çok hızlı yorum yapıyorsun. Lütfen ${remaining} saniye sonra tekrar dene."))
        }

        if (text.isBlank()) {
            return Resource.Error(UiText.DynamicString("Yorum boş olamaz."))
        }

        // The addComment function needs to be added to PostRepository
        // For now, let's assume it will be.
        // val result = postRepository.addComment(postId, text)
        // if (result is Resource.Success) {
        //     rateLimiter.recordAction(LimitableAction.POST_COMMENT)
        // }
        // return result
        return Resource.Error(UiText.DynamicString("addComment not yet implemented in PostRepository"))
    }
}
