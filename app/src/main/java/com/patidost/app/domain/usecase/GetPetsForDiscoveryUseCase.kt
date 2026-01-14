package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPetsForDiscoveryUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    // In a real scenario, this would take filters, user location, etc.
    // and fetch a list of pets from the repository.
    // For now, it will use the featured pets as a stand-in.
    operator fun invoke(): Flow<Resource<List<Pet>>> {
        return homeRepository.getFeaturedPets()
    }
}
