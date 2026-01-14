package com.patidost.app.presentation.ui.screen.conversations

import androidx.lifecycle.ViewModel
import com.patidost.app.domain.model.Conversation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import javax.inject.Inject

data class ConversationsUiState(
    val conversations: List<Conversation> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class ConversationsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ConversationsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadConversations()
    }

    private fun loadConversations() {
        // TODO: Replace with actual data from a repository
        _uiState.value = ConversationsUiState(
            conversations = listOf(
                Conversation(
                    id = "1",
                    participants = listOf("user1", "user2"),
                    lastMessage = "Merhaba! Nasılsın?",
                    lastMessageTimestamp = Date(),
                    unreadMessageCount = 2,
                    otherUserAvatarUrl = "", // Add placeholder or actual URL
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
        )
    }
}
