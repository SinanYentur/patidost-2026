package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use Case to fetch pets belonging to a specific owner.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
class GetPetsUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    /**
     * Returns a reactive flow of pets.
     */
    operator fun invoke(ownerId: String): Flow<List<Pet>> {
        return petRepository.getPetsByOwner(ownerId)
    }
}
