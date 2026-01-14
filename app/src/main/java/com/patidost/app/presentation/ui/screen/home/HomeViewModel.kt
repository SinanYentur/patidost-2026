package com.patidost.app.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Post
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.domain.usecase.GetSocialFeedUseCase
import com.patidost.app.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getSocialFeedUseCase: GetSocialFeedUseCase,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository // Correct repository for getting user ID
) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    val feedState: Flow<PagingData<Post>> = getSocialFeedUseCase()
        .cachedIn(viewModelScope)

    init {
        checkOnboardingStatus()
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            val userId = authRepository.getCurrentUserId() // Correctly call from AuthRepository
            if (userId != null) {
                val petsResult = userRepository.getPetsForUser(userId).first()
                if (petsResult is Resource.Success && petsResult.data.isNullOrEmpty()) {
                    // If the user has no pets, navigate to AddPetScreen
                    _navigationEvent.emit(Screen.AddPet.route)
                }
            }
        }
    }
}
