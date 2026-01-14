package com.patidost.app.data.remote

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Post

interface HomeDataSource {
    suspend fun getPostById(postId: String): Resource<Post?>
    suspend fun toggleLike(postId: String, userId: String): Resource<Unit>
    suspend fun addComment(postId: String, userId: String, text: String): Resource<Unit>
}
