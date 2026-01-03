package com.patidost.app.domain.repository

import com.patidost.app.domain.model.Pet
import kotlinx.coroutines.flow.Flow

/**
 * PetRepository Contract (SSOT) - 2026 Standard.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
interface PetRepository {
    
    /** Returns a reactive flow of pets by owner. */
    fun getPetsByOwner(ownerId: String): Flow<List<Pet>>

    /** Fetches a single pet by ID. */
    fun getPetById(petId: String): Flow<Pet?>

    /** Adds or updates a pet. */
    suspend fun upsertPet(pet: Pet): Result<Unit>

    /** Permanently deletes a pet. */
    suspend fun deletePet(petId: String): Result<Unit>

    /** 
     * Performs atomic adoption using Firestore Transactions.
     */
    suspend fun adoptPet(petId: String, userId: String): Result<Unit>

    /** Syncs local DB with remote Firestore. */
    suspend fun refreshPets(ownerId: String): Result<Unit>
}
