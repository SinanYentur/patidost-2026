
package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.PostDto
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.model.PostAuthor

fun PostDto.toPost(author: PostAuthor, isLiked: Boolean): Post {
    return Post(
        postId = this.id,
        author = author,
        text = this.text,
        imageUrl = this.mediaUrl,
        timestamp = this.createdAt?.time ?: 0L,
        likeCount = this.likeCount,
        isLiked = isLiked,
        relatedPetId = this.petId
    )
}
