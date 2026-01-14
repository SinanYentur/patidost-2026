package com.patidost.app.presentation.ui.screen.friends

import androidx.lifecycle.ViewModel
import com.patidost.app.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class FriendsUiState(
    val friends: List<User> = emptyList(),
    val isLoading: Boolean = false
)

// A simple User model for the dummy data
data class User(val id: String, val name: String, val avatarUrl: String)

@HiltViewModel
class FriendsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FriendsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadFriends()
    }

    private fun loadFriends() {
        // TODO: Replace with actual data from a repository
        _uiState.value = FriendsUiState(
            friends = listOf(
                User(id = "1", name = "Arda", avatarUrl = ""),
                User(id = "2", name = "Ceyda", avatarUrl = ""),
                User(id = "3", name = "Emir", avatarUrl = ""),
                User(id = "4", name = "Melis", avatarUrl = ""),
                User(id = "5", name = "Erkan", avatarUrl = "")
            )
        )
    }
}
