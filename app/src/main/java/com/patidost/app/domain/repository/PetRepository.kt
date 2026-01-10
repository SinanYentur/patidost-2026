package com.patidost.app.domain.repository

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

/**
 * 🛡️ PetRepository - Sovereign Global Standard V10000.70000.
 * Rule 100: Absolute synchronization for 2026 production standards.
 */
interface PetRepository {
    fun getPets(): Flow<List<Pet>>
    fun getPetById(id: String): Flow<Pet?>
    suspend fun addPet(pet: Pet): DomainResult<Unit>
    suspend fun adoptPet(petId: String): DomainResult<Unit>
    suspend fun refreshPets(): DomainResult<Unit>
    suspend fun deletePet(petId: String): DomainResult<Unit>
    suspend fun syncPets(): DomainResult<Unit>
    fun searchPets(query: String): Flow<List<Pet>>
    suspend fun upsertPet(pet: Pet): DomainResult<Unit>
    fun getPetsByOwner(ownerId: String): Flow<List<Pet>>
}
