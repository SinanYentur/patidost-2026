package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import javax.inject.Inject

// This is a placeholder implementation to satisfy the Hilt dependency graph.
// The actual logic will be implemented after the build is stable.
class PetRepositoryImpl @Inject constructor() : PetRepository {
    override suspend fun addPet(pet: Pet): Resource<Unit> {
        return Resource.Error("Not yet implemented.")
    }
}
