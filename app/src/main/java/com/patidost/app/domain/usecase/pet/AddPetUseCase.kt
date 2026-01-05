package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

class AddPetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(pet: Pet): Result<Unit> {
        return petRepository.upsertPet(pet)
    }
}
