package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * 🛡️ AddPetUseCase - Fixed: Synchronized with PetRepository.addPet signature.
 */
class AddPetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(pet: Pet): DomainResult<Unit> {
        return petRepository.addPet(pet)
    }
}
