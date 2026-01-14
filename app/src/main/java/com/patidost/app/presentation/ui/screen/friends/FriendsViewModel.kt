package com.patidost.app.presentation.ui.screen.friends

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.FriendRepository
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

sealed class FriendsEvent {
    object Refresh : FriendsEvent()
}

data class FriendsUiState(
    val friends: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadFriends()
    }

    fun onEvent(event: FriendsEvent) {
        when (event) {
            FriendsEvent.Refresh -> loadFriends()
        }
    }

    private fun loadFriends() {
        friendRepository.getFriends().onEach { result ->
            _uiState.update {
                when (result) {
                    is Resource.Loading -> it.copy(isLoading = true)
                    is Resource.Success -> it.copy(
                        isLoading = false,
                        friends = result.data ?: emptyList(),
                        error = null
                    )
                    is Resource.Error -> it.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
