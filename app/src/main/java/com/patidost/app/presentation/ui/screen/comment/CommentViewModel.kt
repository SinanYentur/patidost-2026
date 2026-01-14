package com.patidost.app.presentation.ui.screen.comment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Comment
import com.patidost.app.domain.usecase.AddCommentUseCase
import com.patidost.app.domain.usecase.GetCommentsUseCase
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<CommentUiState>(CommentUiState.Loading)
    val state = _state.asStateFlow()

    private val postId: String = savedStateHandle.get<String>("postId")!!

    init {
        loadComments()
    }

    private fun loadComments() {
        getCommentsUseCase(postId).onEach { result ->
            _state.value = when (result) {
                is Resource.Loading -> CommentUiState.Loading
                is Resource.Success -> CommentUiState.Success(result.data ?: emptyList())
                is Resource.Error -> CommentUiState.Error(result.message ?: UiText.DynamicString("Yorumlar yüklenemedi."))
            }
        }.launchIn(viewModelScope)
    }

    fun addComment(text: String) {
        viewModelScope.launch {
            // TODO: Handle loading state for comment submission
            val result = addCommentUseCase(postId, text)
            if (result is Resource.Error) {
                // TODO: Show error to the user (e.g., via a Channel/Flow)
            }
        }
    }
}

sealed interface CommentUiState {
    data object Loading : CommentUiState
    data class Success(val comments: List<Comment>) : CommentUiState
    data class Error(val message: UiText) : CommentUiState
}
