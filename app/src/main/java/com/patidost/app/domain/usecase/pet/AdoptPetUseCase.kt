package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

/**
 * Adopt Pet UseCase - V46.24 Rule 97 Signature Fix.
 * RVWL: Synchronized with PetRepository.adoptPet(petId).
 */
class AdoptPetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(petId: String): Result<Unit> {
        return petRepository.adoptPet(petId)
    }
}
