package com.patidost.app.presentation.ui.screen.explore

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.usecase.GetPetsForDiscoveryUseCase
import com.patidost.app.domain.usecase.GetTopGiversUseCase
import com.patidost.app.domain.usecase.GivePatiPointUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPetsForDiscoveryUseCase: GetPetsForDiscoveryUseCase,
    private val givePatiPointUseCase: GivePatiPointUseCase,
    private val getTopGiversUseCase: GetTopGiversUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Restore state from SavedStateHandle or load initial data
        val restoredPetsJson = savedStateHandle.get<String>("pets")
        if (restoredPetsJson != null) {
            val restoredPets = Gson().fromJson(restoredPetsJson, Array<Pet>::class.java).toList()
            _uiState.update { it.copy(pets = restoredPets, isLoading = false) }
        } else {
            loadInitialData()
        }

        // Always load top givers as it's not critical to restore
        viewModelScope.launch {
            loadTopGivers()
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            loadPetsForDiscovery()
        }
    }

    private suspend fun loadTopGivers() {
        getTopGiversUseCase().collect { result ->
            if (result is Resource.Success) {
                _uiState.update { it.copy(topGivers = result.data ?: emptyList()) }
            }
        }
    }

    private suspend fun loadPetsForDiscovery() {
        getPetsForDiscoveryUseCase().collect { result ->
            _uiState.update { currentState ->
                when (result) {
                    is Resource.Success -> {
                        val pets = result.data ?: emptyList()
                        savedStateHandle["pets"] = Gson().toJson(pets)
                        currentState.copy(
                            isLoading = false,
                            pets = pets,
                            error = null
                        )
                    }
                    is Resource.Error -> currentState.copy(
                        isLoading = false,
                        error = result.message
                    )
                    is Resource.Loading -> currentState.copy(isLoading = true)
                }
            }
        }
    }

    fun onEvent(event: ExploreEvent) {
        when (event) {
            is ExploreEvent.SwipeLeft, is ExploreEvent.SwipeRight -> {
                if (event is ExploreEvent.SwipeRight) {
                    val petToGive = uiState.value.pets.firstOrNull() ?: return
                    val fromUserId = "TODO_CURRENT_USER_ID"
                    viewModelScope.launch {
                        givePatiPointUseCase(fromUserId = fromUserId, toPetId = petToGive.id, amount = 1)
                    }
                }
                removeTopPet()
            }
        }
    }

    private fun removeTopPet() {
        val updatedPets = _uiState.value.pets.drop(1)
        savedStateHandle["pets"] = Gson().toJson(updatedPets) // Update SavedStateHandle
        _uiState.update { it.copy(pets = updatedPets) }
    }
}

sealed interface ExploreEvent {
    object SwipeLeft : ExploreEvent
    object SwipeRight : ExploreEvent
}
