package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

/**
 * Use case for fetching the details of a single pet.
 * This will be the dependency for PetDetailViewModel.
 */
class GetPetDetailUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    // The actual implementation will be added later.
    // For now, it returns an error to satisfy the build.
    suspend operator fun invoke(petId: String): Resource<Pet> {
        return Resource.Error("Not yet implemented")
    }
}
