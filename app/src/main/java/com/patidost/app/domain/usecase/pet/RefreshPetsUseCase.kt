package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * 🛡️ RefreshPetsUseCase - Fixed: Standardized DomainResult return type.
 */
class RefreshPetsUseCase @Inject constructor(
    private val repository: PetRepository
) {
    suspend operator fun invoke(): DomainResult<Unit> {
        return repository.refreshPets()
    }
}
