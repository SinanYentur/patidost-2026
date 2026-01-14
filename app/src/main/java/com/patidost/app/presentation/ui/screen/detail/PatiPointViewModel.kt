package com.patidost.app.presentation.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatiPointViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PatiPointUiState>(PatiPointUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = Channel<PatiPointEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    fun givePatiPoints(petId: String, amount: Int) {
        viewModelScope.launch {
            _uiState.value = PatiPointUiState.Loading
            val result = homeRepository.givePatiPoint(petId, amount)

            _uiState.value = PatiPointUiState.Idle // Reset state after operation

            when (result) {
                is Resource.Success -> {
                    val newBalance = result.data?.currentPoints ?: 0
                    _eventFlow.send(PatiPointEvent.ShowDonationSuccess("Başarıyla $amount Pati Puanı gönderildi! Yeni bakiyen: $newBalance"))
                }
                is Resource.Error -> {
                    _eventFlow.send(PatiPointEvent.ShowError(result.message!!))
                }
                is Resource.Loading -> { /* Not used in this flow */ }
            }
        }
    }
}
