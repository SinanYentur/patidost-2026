package com.patidost.app.presentation.pet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.usecase.pet.GetPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 🛡️ Rule 100: Thin ViewModel.
 * 🛡️ Rule 300.1: Process Death Protection via SavedStateHandle.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // 🛡️ Mühür: State preservation for scroll position or filters can be added here
    private val _searchQuery = savedStateHandle.getStateFlow("search_query", "")
    val searchQuery = _searchQuery

    val uiState: StateFlow<HomeUiState> = getPetsUseCase()
        .map { pets ->
            if (pets.isEmpty()) HomeUiState.Empty
            else HomeUiState.Content(pets)
        }
        .onStart { emit(HomeUiState.Loading) }
        .catch { e -> emit(HomeUiState.Error(e.message ?: "Unknown Error")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Idle
        )

    fun onSearchQueryChange(query: String) {
        savedStateHandle["search_query"] = query
    }
}
