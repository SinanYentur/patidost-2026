package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * 🛡️ DeletePetUseCase - Fixed: Synchronized with DomainResult (Rule 100).
 */
class DeletePetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(petId: String): DomainResult<Unit> {
        return petRepository.deletePet(petId)
    }
}
