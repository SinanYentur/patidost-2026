package com.patidost.app.presentation.ui.screen.comment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.AddCommentUseCase
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val addCommentUseCase: AddCommentUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val postId = savedStateHandle.get<String>("postId")!!

    private val _uiState = MutableStateFlow(CommentUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: CommentEvent) {
        when (event) {
            is CommentEvent.CommentChanged -> _uiState.update { it.copy(commentText = event.text) }
            CommentEvent.SubmitComment -> submitComment()
        }
    }

    private fun submitComment() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = addCommentUseCase(postId, _uiState.value.commentText)
            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, isCommentAdded = true) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> { /* No-op */ }
            }
        }
    }
}

data class CommentUiState(
    val commentText: String = "",
    val isLoading: Boolean = false,
    val isCommentAdded: Boolean = false,
    val error: UiText? = null
)

sealed interface CommentEvent {
    data class CommentChanged(val text: String) : CommentEvent
    object SubmitComment : CommentEvent
}
