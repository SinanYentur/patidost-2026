package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

/**
 * Refresh Pets UseCase - V46.25 Rule 97 Signature Fix.
 * RVWL: Corrected to use syncPets() from Repository.
 */
class RefreshPetsUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return petRepository.syncPets()
    }
}
