package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

/**
 * UseCase to refresh the pet list from the remote data source.
 * PD-LOCKS Compliance: Atomic Completeness.
 */
class RefreshPetsUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    /**
     * Triggers the sync process between Remote and Local sources.
     */
    suspend operator fun invoke(ownerId: String): Result<Unit> {
        return petRepository.refreshPets(ownerId)
    }
}
