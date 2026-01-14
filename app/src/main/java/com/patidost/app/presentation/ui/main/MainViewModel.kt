package com.patidost.app.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.usecase.auth.CheckAuthStatusUseCase
import com.patidost.app.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            val result = checkAuthStatusUseCase()
            val startDestination = if (result is Resource.Success && result.data != null) {
                Screen.Explore.route // User is logged in, go to Explore
            } else {
                Screen.Auth.route // User is not logged in, go to Auth
            }
            _uiState.update { it.copy(isLoading = false, startDestination = startDestination) }
        }
    }
}
