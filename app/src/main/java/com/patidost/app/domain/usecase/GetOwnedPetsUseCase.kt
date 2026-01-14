package com.patidost.app.domain.usecase

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching the list of pets owned by a specific user.
 */
class GetOwnedPetsUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    operator fun invoke(userId: String): Flow<List<Pet>> {
        return petRepository.getOwnedPets(userId)
    }
}
