package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.domain.repository.PetFilter
import javax.inject.Inject

class SearchPetsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(query: String, filter: PetFilter): Resource<List<Pet>> {
        return repository.searchPets(query, filter)
    }
}
