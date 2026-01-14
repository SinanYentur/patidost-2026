package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Conversation
import com.patidost.app.domain.model.Message
import com.patidost.app.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

class FakeConversationRepository @Inject constructor() : ConversationRepository {

    override fun getConversations(): Flow<Resource<List<Conversation>>> = flow {
        val conversations = listOf(
            Conversation(
                id = "1",
                participants = listOf("user1", "user2"),
                lastMessage = "Merhaba! Nasılsın?",
                lastMessageTimestamp = Date(),
                unreadMessageCount = 2,
                otherUserAvatarUrl = "",
                otherUserName = "Zeynep"
            ),
            Conversation(
                id = "2",
                participants = listOf("user1", "user3"),
                lastMessage = "Akşam parka gidelim mi? Leo çok sevinir.",
                lastMessageTimestamp = Date(System.currentTimeMillis() - 1000 * 60 * 60),
                unreadMessageCount = 0,
                otherUserAvatarUrl = "",
                otherUserName = "Emre"
            )
        )
        emit(Resource.Success(conversations))
    }

    override fun getMessages(conversationId: String): Flow<List<Message>> = flow {
        val messages = listOf(
            Message("1", "1", "user2", "Merhaba! Nasılsın?", Date(System.currentTimeMillis() - 1000 * 60 * 5), isFromCurrentUser = false),
            Message("2", "1", "user1", "İyiyim, teşekkürler! Sen nasılsın? Parka gittiniz mi?", Date(System.currentTimeMillis() - 1000 * 60 * 4), isFromCurrentUser = true),
            Message("3", "1", "user2", "Gittik, Leo çok eğlendi!", Date(System.currentTimeMillis() - 1000 * 60 * 3), isFromCurrentUser = false),
            Message("4", "1", "user2", "Akşam için bir planın var mı?", Date(System.currentTimeMillis() - 1000 * 60 * 2), isFromCurrentUser = false)
        )
        emit(messages)
    }

    override suspend fun sendMessage(conversationId: String, content: String) {
        // Simulate sending a message. In a real implementation, this would make a network call.
    }
}
