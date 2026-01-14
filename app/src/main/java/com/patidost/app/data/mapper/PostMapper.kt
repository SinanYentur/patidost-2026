package com.patidost.app.data.mapper

import com.patidost.app.data.local.entity.PostEntity
import com.patidost.app.domain.model.Post

/**
 * Maps PostEntity (database model) to Post (domain model).
 */
fun PostEntity.toDomain(): Post {
    return Post(
        postId = this.postId,
        author = this.author,
        text = this.text,
        imageUrl = this.imageUrl,
        timestamp = this.timestamp,
        likeCount = this.likeCount,
        relatedPetId = this.relatedPetId
    )
}
