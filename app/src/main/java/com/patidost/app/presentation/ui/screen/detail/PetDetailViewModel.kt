package com.patidost.app.presentation.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.GetPetDetailUseCase
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val getPetDetailUseCase: GetPetDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val petId = savedStateHandle.get<String>("petId")!!

    private val _uiState = MutableStateFlow(PetDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getPetDetail()
    }

    private fun getPetDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = getPetDetailUseCase(petId)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            pet = result.data
                        )
                    }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> { /* No-op */ }
            }
        }
    }
}
