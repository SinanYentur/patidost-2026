package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

class GivePatiPointUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(petId: String, amount: Int): Resource<User> {
        // 1. Get current user to check balance
        val userResource = authRepository.getCurrentUser()
        val currentUser = (userResource as? Resource.Success)?.data 
            ?: return Resource.Error(userResource.message ?: UiText.DynamicString("Kullanıcı bilgisi alınamadı."))

        // 2. Business Logic: Check for sufficient balance
        if (currentUser.patiPoints < amount) {
            return Resource.Error(UiText.DynamicString("Yetersiz Pati Puanı."))
        }

        // 3. Perform the donation
        val donationResult = homeRepository.givePatiPoint(petId, amount)

        // 4. Return the final state
        return when(donationResult) {
            is Resource.Success -> {
                // On success, return the updated user object
                val updatedUser = currentUser.copy(patiPoints = donationResult.data!!.currentPoints)
                Resource.Success(updatedUser)
            }
            is Resource.Error -> {
                Resource.Error(donationResult.message ?: UiText.DynamicString("Bağış işlemi başarısız."))
            }
            is Resource.Loading -> Resource.Loading() // Should not happen here
        }
    }
}
