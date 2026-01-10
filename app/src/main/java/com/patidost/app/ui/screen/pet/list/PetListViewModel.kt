package com.patidost.app.ui.screen.pet.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.usecase.pet.GetPetsUseCase
import com.patidost.app.domain.usecase.pet.RefreshPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 🛡️ PetListViewModel - V10000.70029 Sovereign Implementation.
 * Rule 100: Physical evidence of state management.
 */
@HiltViewModel
class PetListViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val refreshPetsUseCase: RefreshPetsUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    
    val uiState: StateFlow<PetListUiState> = getPetsUseCase()
        .combine(_isLoading) { pets, loading ->
            PetListUiState(
                pets = pets,
                isLoading = loading
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PetListUiState()
        )

    init {
        refreshPets()
    }

    private fun refreshPets() {
        viewModelScope.launch {
            _isLoading.value = true
            refreshPetsUseCase()
            _isLoading.value = false
        }
    }
}

data class PetListUiState(
    val pets: List<Pet> = emptyList(),
    val isLoading: Boolean = false
)
