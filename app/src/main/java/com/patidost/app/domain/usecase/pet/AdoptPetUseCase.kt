package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

/**
 * UseCase to handle pet adoption logic.
 * RVWL: Android Domain Layer guidelines (Business Logic Encapsulation).
 */
class AdoptPetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    /**
     * Executes the adoption process.
     * Returns Result<Unit> to bubble up Firestore Transaction results.
     */
    suspend operator fun invoke(petId: String, userId: String): Result<Unit> {
        return petRepository.adoptPet(petId, userId)
    }
}
