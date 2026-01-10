package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * 🛡️ AdoptPetUseCase - Fixed: Standardized DomainResult return type.
 */
class AdoptPetUseCase @Inject constructor(
    private val repository: PetRepository
) {
    suspend operator fun invoke(petId: String): DomainResult<Unit> {
        return repository.adoptPet(petId)
    }
}
