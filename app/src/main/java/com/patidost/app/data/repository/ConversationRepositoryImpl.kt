package com.patidost.app.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.patidost.app.core.util.Resource
import com.patidost.app.presentation.ui.util.UiText
import com.patidost.app.data.mapper.toConversation
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.remote.dto.MessageDto
import com.patidost.app.domain.model.Conversation
import com.patidost.app.domain.model.Message
import com.patidost.app.domain.repository.ConversationRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ConversationRepository {

    override fun getConversations(): Flow<Resource<List<Conversation>>> = callbackFlow {
        trySend(Resource.Loading())

        val userId = auth.currentUser?.uid
        if (userId == null) {
            trySend(Resource.Error(UiText.DynamicString("User not logged in")))
            awaitClose { }
            return@callbackFlow
        }

        val listener = firestore.collection("conversations")
            .whereArrayContains("participants", userId)
            .orderBy("lastMessageTimestamp", Query.Direction.DESCENDING)
            .limit(50) // Performance Vaccination: Prevent unlimited reads
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Resource.Error(UiText.DynamicString(error.message ?: "Unknown error")))
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val conversations = snapshot.documents.mapNotNull { it.toConversation(userId) }
                    trySend(Resource.Success(conversations))
                }
            }
        
        awaitClose { listener.remove() }
    }

    override fun getMessages(conversationId: String): Flow<List<Message>> = callbackFlow {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            close(IllegalStateException("User not logged in"))
            return@callbackFlow
        }

        val listener = firestore.collection("conversations").document(conversationId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING) // Get latest messages first
            .limit(100) // Performance Vaccination: Prevent unlimited reads
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val messages = snapshot.toObjects(MessageDto::class.java).mapIndexed { index, messageDto ->
                        messageDto.toDomain(snapshot.documents[index].id, conversationId, userId)
                    }.reversed() // Reverse to show oldest first in UI
                    trySend(messages)
                }
            }
        
        awaitClose { listener.remove() }
    }

    override suspend fun sendMessage(conversationId: String, content: String) {
        val userId = auth.currentUser?.uid ?: return

        val messageDto = MessageDto(
            senderId = userId,
            content = content,
            timestamp = Timestamp.now()
        )

        val conversationRef = firestore.collection("conversations").document(conversationId)
        val newMessageRef = conversationRef.collection("messages").document()

        firestore.runBatch {
            it.set(newMessageRef, messageDto)
            it.update(conversationRef, mapOf(
                "lastMessage" to content,
                "lastMessageTimestamp" to messageDto.timestamp
            ))
        }.await()
    }
}
