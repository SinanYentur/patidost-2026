package com.patidost.app.presentation.ui.screen.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.domain.repository.PetFilter
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExploreUiState>(ExploreUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _filter = MutableStateFlow(PetFilter())
    val filter = _filter.asStateFlow()

    init {
        _searchQuery
            .debounce(500L) // Debounce operator to prevent rapid firing
            .onEach { query ->
                // Only trigger search if query is not blank, or if filters are applied
                if (query.isNotBlank() || _filter.value != PetFilter()) {
                    performSearch(query, _filter.value)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onFilterChanged(filter: PetFilter) {
        _filter.value = filter
        // Re-trigger search with the new filter
        performSearch(_searchQuery.value, filter)
    }

    private fun performSearch(query: String, filter: PetFilter) {
        viewModelScope.launch {
            _uiState.value = ExploreUiState.Loading
            when (val result = homeRepository.searchPets(query, filter)) {
                is Resource.Success -> {
                    _uiState.value = ExploreUiState.Success(
                        pets = result.data ?: emptyList(),
                        query = query,
                        filter = filter
                    )
                }
                is Resource.Error -> {
                    _uiState.value = ExploreUi-State.Error(result.message ?: UiText.DynamicString("Search failed."))
                }
                 is Resource.Loading -> { /* Not used in this flow */ }
            }
        }
    }
}
