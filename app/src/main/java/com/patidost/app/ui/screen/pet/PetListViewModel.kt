package com.patidost.app.ui.screen.pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.R
import com.patidost.app.domain.usecase.auth.GetAuthStateUseCase
import com.patidost.app.domain.usecase.pet.GetPetsUseCase
import com.patidost.app.domain.usecase.pet.RefreshPetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Pet List screen.
 * RVWL: Synchronized with string resources for localization (Rule 35).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PetListViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val getPetsUseCase: GetPetsUseCase,
    private val refreshPetsUseCase: RefreshPetsUseCase
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    private val _errorResId = MutableStateFlow<Int?>(null)

    /**
     * Re-active UI State.
     * Uses resource IDs instead of raw strings for architecture compliance.
     */
    val uiState: StateFlow<PetListUiState> = getAuthStateUseCase()
        .flatMapLatest { user ->
            if (user == null || user.id.isEmpty()) {
                flowOf(PetListUiState.Error(R.string.error_session_not_found))
            } else {
                combine(
                    getPetsUseCase(user.id),
                    _isRefreshing,
                    _errorResId
                ) { pets, isRefreshing, errorId ->
                    when {
                        errorId != null -> PetListUiState.Error(errorId)
                        pets.isEmpty() && !isRefreshing -> PetListUiState.Loading
                        else -> PetListUiState.Success(pets = pets, isRefreshing = isRefreshing)
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PetListUiState.Loading
        )

    fun refreshPets() {
        viewModelScope.launch {
            getAuthStateUseCase().firstOrNull()?.let { user ->
                _isRefreshing.update { true }
                _errorResId.update { null }
                
                refreshPetsUseCase(user.id).onFailure {
                    _errorResId.update { R.string.error_refresh_failed }
                }
                
                _isRefreshing.update { false }
            }
        }
    }
}
