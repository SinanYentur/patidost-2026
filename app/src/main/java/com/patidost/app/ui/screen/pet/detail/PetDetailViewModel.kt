package com.patidost.app.ui.screen.pet.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 🛡️ PetDetailViewModel - V10000.70048 Sovereign Seal.
 * Rule 300.1: State preservation via SavedStateHandle.
 * Rule 420: Restored from Purge to fix dependency chain.
 */
@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val petRepository: PetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val petId: String = checkNotNull(savedStateHandle["petId"])

    val uiState: StateFlow<PetDetailUiState> = petRepository.getPetById(petId)
        .map { pet ->
            if (pet != null) PetDetailUiState.Success(pet)
            else PetDetailUiState.Error("Dost bulunamadı.")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PetDetailUiState.Loading
        )
}

sealed interface PetDetailUiState {
    object Loading : PetDetailUiState
    data class Success(val pet: Pet) : PetDetailUiState
    data class Error(val message: String) : PetDetailUiState
}
