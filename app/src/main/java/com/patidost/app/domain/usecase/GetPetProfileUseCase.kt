package com.patidost.app.domain.usecase

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

/**
 * Use case for fetching the profile of a single pet.
 */
class GetPetProfileUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(petId: String): Pet? {
        return petRepository.getPetProfile(petId)
    }
}
