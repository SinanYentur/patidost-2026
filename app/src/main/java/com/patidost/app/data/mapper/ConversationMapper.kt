package com.patidost.app.data.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.patidost.app.data.dto.ConversationDto
import com.patidost.app.domain.model.Conversation
import java.util.Date

/**
 * Maps a ConversationDto from the data layer to a Conversation in the domain layer.
 */
fun ConversationDto.toDomain(id: String, currentUserId: String): Conversation {
    val otherParticipant = participants?.firstOrNull { it != currentUserId } ?: ""
    // In a real app, you would fetch user details (name, avatar) based on the otherParticipant ID
    return Conversation(
        id = id,
        participants = participants ?: emptyList(),
        lastMessage = lastMessage ?: "",
        lastMessageTimestamp = lastMessageTimestamp?.toDate() ?: Date(),
        unreadMessageCount = 0, // This would be calculated separately
        otherUserAvatarUrl = "", // Fetch from user profile
        otherUserName = "User $otherParticipant" // Fetch from user profile
    )
}

/**
 * Maps a Firestore DocumentSnapshot to a Conversation in the domain layer.
 */
fun DocumentSnapshot.toConversation(currentUserId: String): Conversation? {
    return try {
        val dto = this.toObject(ConversationDto::class.java)
        dto?.toDomain(this.id, currentUserId)
    } catch (e: Exception) {
        // Log the exception
        null
    }
}
