package com.patidost.app.ui.screen.pet.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val petRepository: PetRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val petId: String = checkNotNull(savedStateHandle["petId"])

    private val _uiState = MutableStateFlow<PetDetailUiState>(PetDetailUiState.Loading)
    val uiState: StateFlow<PetDetailUiState> = _uiState.asStateFlow()

    init {
        loadPet()
    }

    private fun loadPet() {
        viewModelScope.launch {
            petRepository.getPetById(petId).collect { pet ->
                _uiState.value = if (pet != null) {
                    PetDetailUiState.Success(pet)
                } else {
                    PetDetailUiState.Error("Pet not found")
                }
            }
        }
    }

    fun onAdoptPet(id: String) { // Fixed: Argument added to match Screen call
        viewModelScope.launch {
            petRepository.adoptPet(id)
        }
    }
}
