package com.patidost.app.data.mapper

import com.patidost.app.data.remote.dto.MessageDto
import com.patidost.app.domain.model.Message
import java.util.Date

/**
 * Maps a MessageDto from the data layer to a Message in the domain layer.
 */
fun MessageDto.toDomain(id: String, conversationId: String, currentUserId: String): Message {
    return Message(
        id = id,
        conversationId = conversationId,
        senderId = this.senderId ?: "",
        content = this.content ?: "",
        timestamp = this.timestamp?.toDate() ?: Date(),
        isFromCurrentUser = this.senderId == currentUserId
    )
}
