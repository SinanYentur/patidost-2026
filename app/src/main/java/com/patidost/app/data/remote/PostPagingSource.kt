package com.patidost.app.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.patidost.app.domain.model.Post
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : PagingSource<QuerySnapshot, Post>() {

    override fun getRefreshKey(state: PagingState<QuerySnapshot, Post>): QuerySnapshot? {
        return null // Simplest approach: always refresh from the start.
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Post> {
        return try {
            // Start query after the last document from the previous page
            val currentPage = params.key ?: firestore.collection("posts")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(20)
                .get()
                .await()

            val posts = currentPage.toObjects(Post::class.java)

            // If there are no more posts, we set nextKey to null
            val nextKey = if (currentPage.isEmpty) null else {
                val lastVisiblePost = currentPage.documents[currentPage.size() - 1]
                firestore.collection("posts")
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .startAfter(lastVisiblePost)
                    .limit(20)
                    .get()
                    .await()
            }

            LoadResult.Page(
                data = posts,
                prevKey = null, // Only paging forward
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
