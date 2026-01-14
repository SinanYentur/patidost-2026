package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet

/**
 * Interface for pet-related data operations.
 * This defines the contract that the data layer must implement.
 */
interface PetRepository {
    suspend fun addPet(pet: Pet): Resource<Unit>
    // Add other pet-related functions here, e.g.:
    // suspend fun getPet(petId: String): Resource<Pet>
    // suspend fun getPetsForUser(userId: String): Resource<List<Pet>>
}
