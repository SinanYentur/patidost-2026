package com.patidost.app.presentation.ui.screen.chat

import androidx.lifecycle.ViewModel
import com.patidost.app.domain.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import javax.inject.Inject

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val conversationPartnerName: String = ""
)

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadMessages("1") // Dummy conversationId
    }

    private fun loadMessages(conversationId: String) {
        // TODO: Replace with actual data from a repository
        _uiState.value = ChatUiState(
            conversationPartnerName = "Zeynep",
            messages = listOf(
                Message("1", "1", "user2", "Merhaba! Nasılsın?", Date(System.currentTimeMillis() - 1000 * 60 * 5), isFromCurrentUser = false),
                Message("2", "1", "user1", "İyiyim, teşekkürler! Sen nasılsın? Parka gittiniz mi?", Date(System.currentTimeMillis() - 1000 * 60 * 4), isFromCurrentUser = true),
                Message("3", "1", "user2", "Gittik, Leo çok eğlendi!", Date(System.currentTimeMillis() - 1000 * 60 * 3), isFromCurrentUser = false),
                Message("4", "1", "user2", "Akşam için bir planın var mı?", Date(System.currentTimeMillis() - 1000 * 60 * 2), isFromCurrentUser = false)
            )
        )
    }
}
