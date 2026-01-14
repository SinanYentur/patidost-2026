package com.patidost.app.presentation.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val homeRepository: HomeRepository // Assuming we get details from here for now
) : ViewModel() {

    private val _uiState = MutableStateFlow(PetDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val petId: String = savedStateHandle.get<String>("petId") ?: ""

    init {
        loadPetDetails()
    }

    private fun loadPetDetails() {
        if (petId.isBlank()) {
            _uiState.update { it.copy(isLoading = false, error = "Pet ID is missing.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // This is a simulation. In a real scenario, you'd have a getPetById method.
            val featuredPetResult = homeRepository.getFeaturedPet()

            featuredPetResult.fold(
                onSuccess = { pet ->
                    if (pet != null && pet.patiId == petId) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                imageUrl = pet.imageUrl,
                                description = "Bu güzel dostumuz yeni yuvasını arıyor. Ona sıcak bir yuva sunmak ister misiniz?", // Fake description
                                age = pet.age,
                                breed = pet.breed,
                                healthStatus = "Sağlıklı ve aşıları tam.", // Fake status
                                isFavorite = false // Default state
                            )
                        }
                    } else {
                        _uiState.update { it.copy(isLoading = false, error = "Pet not found.") }
                    }
                },
                onFailure = { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message) }
                }
            )
        }
    }

    fun toggleFavorite() {
        _uiState.update { it.copy(isFavorite = !it.isFavorite) }
    }
}
