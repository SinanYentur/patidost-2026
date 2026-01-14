package com.patidost.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.local.dao.PostDao
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.remote.ApiService
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.repository.PostRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val apiService: ApiService,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
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

    override suspend fun likePost(postId: String) {
        postDao.updateLikeStatus(postId, isLiked = true, likeCountChange = 1)
        try {
            apiService.likePost(postId)
        } catch (e: Exception) {
            postDao.updateLikeStatus(postId, isLiked = false, likeCountChange = -1)
            throw e
        }
    }

    override suspend fun unlikePost(postId: String) {
        postDao.updateLikeStatus(postId, isLiked = false, likeCountChange = -1)
        try {
            apiService.unlikePost(postId)
        } catch (e: Exception) {
            postDao.updateLikeStatus(postId, isLiked = true, likeCountChange = 1)
            throw e
        }
    }

    override suspend fun createPost(petId: String, text: String, mediaUrl: String?) {
        val currentUser = auth.currentUser
        require(currentUser != null) { "User must be authenticated to create a post" }

        val postData = mapOf(
            "ownerId" to currentUser.uid,
            "petId" to petId,
            "text" to text,
            "mediaUrl" to mediaUrl,
            "likeCount" to 0,
            "commentCount" to 0,
            "createdAt" to FieldValue.serverTimestamp()
        )

        firestore.collection("posts").add(postData).await()
    }

    override suspend fun addComment(postId: String, text: String): Resource<Unit> {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            return Resource.Error(UiText.DynamicString("User not authenticated"))
        }

        val commentData = mapOf(
            "userId" to currentUser.uid,
            "text" to text,
            "createdAt" to FieldValue.serverTimestamp()
        )

        return try {
            val postRef = firestore.collection("posts").document(postId)
            firestore.runTransaction {
                transaction ->
                transaction.update(postRef, "commentCount", FieldValue.increment(1))
                val newCommentRef = postRef.collection("comments").document()
                transaction.set(newCommentRef, commentData)
                null
            }.await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred"))
        }
    }
}
