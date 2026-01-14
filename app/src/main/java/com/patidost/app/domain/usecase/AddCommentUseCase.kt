package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.CommentRepository
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(postId: String, text: String): Resource<Unit> {
        if (text.isBlank()) {
            return Resource.Error(com.patidost.app.presentation.ui.util.UiText.DynamicString("Yorum boş olamaz."))
        }
        return commentRepository.addComment(postId, text)
    }
}
