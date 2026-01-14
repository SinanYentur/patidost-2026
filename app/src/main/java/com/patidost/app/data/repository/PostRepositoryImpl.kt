package com.patidost.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.patidost.app.data.local.dao.PostDao
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDao: PostDao
) : PostRepository {

    override fun getSocialFeed(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { postDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { postEntity ->
                postEntity.toDomain()
            }
        }
    }
}
