package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.data.local.dao.CommentDao
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.remote.ApiService
import com.patidost.app.data.util.networkBoundResource
import com.patidost.app.domain.model.Comment
import com.patidost.app.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentDao: CommentDao,
    private val apiService: ApiService // Injected for future use (fetching comments)
) : CommentRepository {

    override fun getCommentsForPost(postId: String): Flow<Resource<List<Comment>>> = networkBoundResource(
        query = {
            commentDao.getCommentsForPost(postId).map { entities ->
                entities.map { it.toDomain() }
            }
        },
        fetch = {
            // TODO: apiService.getCommentsForPost(postId)
            emptyList() // Returning empty list as network fetch is not implemented yet
        },
        saveFetchResult = { remoteComments ->
            // TODO: Map DTOs to Entities and upsert to commentDao
        }
    )

    override suspend fun addComment(postId: String, text: String): Resource<Unit> {
        // TODO: Implement API call and local DB update
        return Resource.Success(Unit)
    }
}
