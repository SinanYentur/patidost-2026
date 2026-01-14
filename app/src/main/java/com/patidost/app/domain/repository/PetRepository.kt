package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the repository that handles Pet data.
 */
interface PetRepository {

    /**
     * Fetches a list of pets owned by a specific user.
     */
    fun getOwnedPets(userId: String): Flow<List<Pet>>

    /**
     * Fetches the profile details for a single pet.
     */
    suspend fun getPetProfile(petId: String): Pet?

    /**
     * Adds a new pet to the system.
     */
    suspend fun addPet(pet: Pet): Resource<Unit>

}
