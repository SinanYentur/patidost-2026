package com.patidost.app.presentation.ui.screen.add_pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.PetOwner
import com.patidost.app.domain.usecase.AddPetUseCase
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val addPetUseCase: AddPetUseCase
    // TODO: Inject UserRepository to get the real owner
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddPetUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<Unit>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEvent(event: AddPetEvent) {
        when (event) {
            is AddPetEvent.NameChanged -> _uiState.update { it.copy(name = event.name) }
            is AddPetEvent.BreedChanged -> _uiState.update { it.copy(breed = event.breed) }
            is AddPetEvent.AgeChanged -> _uiState.update { it.copy(age = event.age) }
            is AddPetEvent.AddPet -> addPet()
        }
    }

    private fun addPet() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // Temporary owner - This should be replaced with the actual logged-in user
            val tempOwner = PetOwner("temp-user-id", "Temporary User", "")

            val pet = Pet(
                id = UUID.randomUUID().toString(), // Backend should ideally handle this
                name = _uiState.value.name,
                breed = _uiState.value.breed,
                age = _uiState.value.age.toIntOrNull() ?: 0,
                imageUrl = "", // This will be set after image upload
                patiPoints = 0, // Starts with 0 pati points
                owner = tempOwner
            )

            when (val result = addPetUseCase(pet)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, isPetAdded = true) }
                    _navigationEvent.emit(Unit) // Navigate back on success
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> { /* No-op */ }
            }
        }
    }
}

sealed interface AddPetEvent {
    data class NameChanged(val name: String) : AddPetEvent
    data class BreedChanged(val breed: String) : AddPetEvent
    data class AgeChanged(val age: String) : AddPetEvent
    object AddPet : AddPetEvent
}
