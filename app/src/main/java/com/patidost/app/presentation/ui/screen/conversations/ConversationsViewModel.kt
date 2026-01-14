package com.patidost.app.presentation.ui.screen.conversations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Conversation
import com.patidost.app.domain.repository.ConversationRepository
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class ConversationsUiState(
    val conversations: List<Conversation> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)

@HiltViewModel
class ConversationsViewModel @Inject constructor(
    private val repository: ConversationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConversationsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadConversations()
    }

    private fun loadConversations() {
        repository.getConversations().onEach { result ->
            _uiState.value = when (result) {
                is Resource.Success -> {
                    ConversationsUiState(conversations = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    ConversationsUiState(error = result.message)
                }
                is Resource.Loading -> {
                    ConversationsUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
