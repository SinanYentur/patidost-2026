package com.patidost.app.presentation.ui.screen.add_pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.AddPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val addPetUseCase: AddPetUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddPetUiState())
    val uiState = _uiState.asStateFlow()

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name, isFormValid = validateForm(name, it.breed, it.age)) }
    }

    fun onBreedChange(breed: String) {
        _uiState.update { it.copy(breed = breed, isFormValid = validateForm(it.name, breed, it.age)) }
    }

    fun onAgeChange(age: String) {
        _uiState.update { it.copy(age = age, isFormValid = validateForm(it.name, it.breed, age)) }
    }

    fun onAddPetClicked() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = addPetUseCase(
                name = _uiState.value.name,
                breed = _uiState.value.breed,
                age = _uiState.value.age.toIntOrNull() ?: 0,
                imageUrl = _uiState.value.imageUrl // Assuming imageUrl is handled elsewhere
            )

            _uiState.update { 
                when(result) {
                    is Resource.Success -> it.copy(isLoading = false, error = null)
                    is Resource.Error -> it.copy(isLoading = false, error = result.message)
                    is Resource.Loading -> it.copy(isLoading = true, error = null)
                }
            }
        }
    }

    private fun validateForm(name: String, breed: String, age: String): Boolean {
        return name.isNotBlank() && breed.isNotBlank() && age.toIntOrNull() != null && age.toInt() > 0
    }
}
