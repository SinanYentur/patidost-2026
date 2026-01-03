package com.patidost.app.ui.screen.pet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.R
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.domain.usecase.pet.AdoptPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Pet Detail Screen - 2026 Standard.
 * RVWL: Synchronized with localized UI State and com.patidost.app identity.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val petRepository: PetRepository,
    private val userRepository: UserRepository,
    private val adoptPetUseCase: AdoptPetUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val petId: String = checkNotNull(savedStateHandle["petId"])
    
    private val _isAdopting = MutableStateFlow(false)
    private val _errorResId = MutableStateFlow<Int?>(null) // Corrected to Resource ID

    /**
     * Re-active UI State.
     * Uses Int Resource IDs for 100% localization parity.
     */
    val uiState: StateFlow<PetDetailUiState> = combine(
        petRepository.getPetById(petId),
        _isAdopting,
        _errorResId
    ) { pet, isAdopting, errorId ->
        when {
            errorId != null -> PetDetailUiState.Error(errorId)
            pet == null -> PetDetailUiState.Loading
            else -> PetDetailUiState.Success(
                pet = pet,
                isAdopted = pet.ownerId.isNotEmpty(),
                isAdopting = isAdopting
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PetDetailUiState.Loading
    )

    /**
     * Executes the adoption flow.
     */
    fun adoptPet() {
        viewModelScope.launch {
            val user = userRepository.getCurrentUser().firstOrNull()
            if (user == null || user.id.isEmpty()) {
                _errorResId.update { R.string.error_session_not_found }
                return@launch
            }

            _isAdopting.update { true }
            _errorResId.update { null }

            adoptPetUseCase(petId, user.id).onFailure {
                _errorResId.update { R.string.error_adoption_failed }
            }
            
            _isAdopting.update { false }
        }
    }
}
