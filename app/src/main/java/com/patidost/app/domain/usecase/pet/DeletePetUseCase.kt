package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

/**
 * Use Case to remove a pet.
 */
class DeletePetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(petId: String): Result<Unit> {
        return petRepository.deletePet(petId)
    }
}
