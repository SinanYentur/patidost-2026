package com.patidost.app.domain.usecase

import com.patidost.app.domain.repository.PostRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(petId: String, text: String, mediaUrl: String?) {
        postRepository.createPost(petId, text, mediaUrl)
    }
}
