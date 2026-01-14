package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Conversation
import com.patidost.app.domain.model.Message
import kotlinx.coroutines.flow.Flow

/**
 * The constitutional contract for managing conversation data.
 * This interface decouples the domain and presentation layers from the data layer.
 */
interface ConversationRepository {

    /**
     * Observes the list of conversations for the current user.
     * @return A Flow emitting the resource-wrapped list of conversations.
     */
    fun getConversations(): Flow<Resource<List<Conversation>>>

    /**
     * Observes the messages within a specific conversation.
     * @param conversationId The ID of the conversation to observe.
     * @return A Flow emitting the list of messages.
     */
    fun getMessages(conversationId: String): Flow<List<Message>>

    /**
     * Sends a new message.
     * @param conversationId The ID of the conversation.
     * @param content The text content of the message.
     */
    suspend fun sendMessage(conversationId: String, content: String)
}
