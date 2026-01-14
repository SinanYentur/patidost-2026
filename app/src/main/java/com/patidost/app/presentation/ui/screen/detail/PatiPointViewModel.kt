package com.patidost.app.presentation.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.GivePatiPointUseCase
import com.patidost.app.presentation.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatiPointViewModel @Inject constructor(
    private val givePatiPointUseCase: GivePatiPointUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PatiPointUiState())
    val uiState = _uiState.asStateFlow()

    fun givePatiPoints(fromUserId: String, toPetId: String, amount: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            // TODO: Actually get the fromUserId from a user repository/session manager
            val result = givePatiPointUseCase(fromUserId, toPetId, amount)

            when (result) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, success = true) }
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
