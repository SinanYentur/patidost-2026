package com.patidost.app.presentation.ui.screen.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.model.Message
import com.patidost.app.domain.repository.ConversationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val conversationPartnerName: String = ""
)

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ConversationRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    private val conversationId: String = savedStateHandle.get<String>("conversationId")!!

    init {
        loadMessages()
    }

    private fun loadMessages() {
        repository.getMessages(conversationId).onEach {
            // TODO: Get partner name from another source
            _uiState.value = ChatUiState(messages = it, conversationPartnerName = "Zeynep")
        }.launchIn(viewModelScope)
    }
}
