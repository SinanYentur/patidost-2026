package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.EconomyRepository
import javax.inject.Inject

/**
 * Use case to handle the business logic of giving a "pati" to a pet.
 */
class GivePatiUseCase @Inject constructor(
    private val economyRepository: EconomyRepository
) {
    suspend operator fun invoke(petId: String): Resource<Unit> {
        // Future logic can be added here, e.g., checking for cooldowns, user status, etc.
        return economyRepository.givePati(targetPetId = petId)
    }
}
