package com.patidost.app.presentation.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.GetProfileDataUseCase
import com.patidost.app.domain.usecase.economy.GetSubscriptionUseCase
import com.patidost.app.domain.usecase.economy.GetWalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val getWalletUseCase: GetWalletUseCase,
    private val getSubscriptionUseCase: GetSubscriptionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userId: String = savedStateHandle.get<String>("userId") ?: "CURRENT_USER_ID_FALLBACK"

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        getProfileDataUseCase(userId).onEach { result ->
            _uiState.update {
                when (result) {
                    is Resource.Success -> it.copy(user = result.data, isLoading = false, error = null)
                    is Resource.Error -> it.copy(error = result.message, isLoading = false)
                    is Resource.Loading -> it.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

        viewModelScope.launch {
            getWalletUseCase().collect { result ->
                if (result is Resource.Success) {
                    _uiState.update { it.copy(wallet = result.data) }
                }
            }
        }

        viewModelScope.launch {
            getSubscriptionUseCase().collect { result ->
                if (result is Resource.Success) {
                    _uiState.update { it.copy(subscription = result.data) }
                }
            }
        }
    }
}
