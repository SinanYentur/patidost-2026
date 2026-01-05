package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * 🛡️ Rule 100: Pure Domain Implementation.
 * Fetches the Single Source of Truth (SSOT) stream of pets.
 */
class GetPetsUseCase @Inject constructor(
    private val repository: PetRepository
) {
    operator fun invoke(): Flow<List<Pet>> = repository.getPets()
}
