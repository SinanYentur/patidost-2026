package com.patidost.app.domain.usecase

import androidx.paging.PagingData
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomeFeedUseCase @Inject constructor(
    private val postRepository: PostRepository // Corrected repository
) {
    operator fun invoke(): Flow<PagingData<Post>> {
        return postRepository.getSocialFeed() // Corrected function name
    }
}
