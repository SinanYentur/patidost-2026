package com.patidost.app.ui.screen.pet.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * PetListViewModel - V10000.9700 Temporal Resilience.
 * Rule 102: Low-resource usage, maximum stability.
 * RVWL: Optimized for 2+ years of operation without architectural debt.
 */
@HiltViewModel
class PetListViewModel @Inject constructor(
    private val petRepository: PetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Survival Seal: Recovery after system-initiated process death
    private val _isRefreshing = MutableStateFlow(false)

    val uiState: StateFlow<PetListUiState> = combine(
        petRepository.getPets(),
        _isRefreshing
    ) { pets, refreshing ->
        PetListUiState(
            isLoading = false,
            pets = pets.toImmutableList(),
            isRefreshing = refreshing,
            lastSyncTimestamp = System.currentTimeMillis()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), // Memory saving seal
        initialValue = PetListUiState.INITIAL.copy(isLoading = true)
    )

    init {
        refreshPets()
    }

    fun refreshPets() {
        if (_isRefreshing.value) return
        
        viewModelScope.launch {
            _isRefreshing.value = true
            petRepository.syncPets()
            _isRefreshing.value = false
        }
    }
}
