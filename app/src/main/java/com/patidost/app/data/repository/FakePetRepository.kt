package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakePetRepository @Inject constructor() : PetRepository {

    override fun getOwnedPets(userId: String): Flow<List<Pet>> {
        // Return an empty list for the fake implementation
        return flowOf(emptyList())
    }

    override suspend fun getPetProfile(petId: String): Pet? {
        // Return null for the fake implementation
        return null
    }

    override suspend fun addPet(pet: Pet): Resource<Unit> {
        // Simulate a successful operation
        println("FakePetRepository: addPet called for ${pet.name}")
        return Resource.Success(Unit)
    }
}
