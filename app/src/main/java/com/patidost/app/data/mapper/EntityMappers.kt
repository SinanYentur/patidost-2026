package com.patidost.app.data.mapper

import com.patidost.app.data.local.entity.PostEntity
import com.patidost.app.domain.model.Post
import com.patidost.app.data.local.entity.PostAuthor as EntityPostAuthor
import com.patidost.app.domain.model.PostAuthor as DomainPostAuthor

/**
 * Converts a PostEntity from the local database to a Post domain model.
 */
fun PostEntity.toDomain(): Post {
    return Post(
        postId = this.postId,
        author = this.author.toDomain(),
        text = this.text,
        imageUrl = this.imageUrl,
        timestamp = this.timestamp,
        likeCount = this.likeCount,
        isLiked = this.isLiked,
        relatedPetId = this.relatedPetId
    )
}

/**
 * Converts a PostAuthor from the local database to a PostAuthor domain model.
 */
fun EntityPostAuthor.toDomain(): DomainPostAuthor {
    return DomainPostAuthor(
        id = this.id,
        name = this.name,
        avatarUrl = this.avatarUrl
    )
}
