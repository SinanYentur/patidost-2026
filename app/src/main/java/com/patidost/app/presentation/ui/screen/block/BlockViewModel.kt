package com.patidost.app.presentation.ui.screen.block

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.BlockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class BlockedUsersUiState(
    val blockedUsers: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class BlockViewModel @Inject constructor(
    private val blockRepository: BlockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BlockedUsersUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBlockedUsers()
    }

    private fun loadBlockedUsers() {
        blockRepository.getBlockedUsers().onEach { result ->
            _uiState.update {
                when (result) {
                    is Resource.Loading -> it.copy(isLoading = true)
                    is Resource.Success -> it.copy(
                        isLoading = false,
                        blockedUsers = result.data ?: emptyList(),
                        error = null
                    )
                    is Resource.Error -> it.copy(
                        isLoading = false,
                        error = result.message?.toString() ?: "An unknown error occurred"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
