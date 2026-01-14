package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.HomeRepository
import javax.inject.Inject

class GetPetByIdUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id: String): Resource<Pet> {
        return repository.getPetById(id)
    }
}
