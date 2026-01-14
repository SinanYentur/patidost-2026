package com.patidost.app.data.mapper

import com.patidost.app.data.local.entity.CommentEntity
import com.patidost.app.domain.model.Comment
import com.patidost.app.data.local.entity.CommentAuthor as EntityCommentAuthor
import com.patidost.app.domain.model.CommentAuthor as DomainCommentAuthor

/**
 * Maps CommentEntity (database model) to Comment (domain model).
 */
fun CommentEntity.toDomain(): Comment {
    return Comment(
        commentId = this.commentId,
        postId = this.postId,
        author = this.author.toDomain(),
        text = this.text,
        timestamp = this.timestamp
    )
}

/**
 * Maps the embedded CommentAuthor from the entity layer to the domain layer.
 */
fun EntityCommentAuthor.toDomain(): DomainCommentAuthor {
    return DomainCommentAuthor(
        id = this.id,
        name = this.name,
        avatarUrl = this.avatarUrl
    )
}
