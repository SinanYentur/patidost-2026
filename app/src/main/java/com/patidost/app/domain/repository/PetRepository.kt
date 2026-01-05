package com.patidost.app.domain.repository

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.flow.Flow

/**
 * 🛡️ Rule 100: Domain Contract for Persistence and Sync.
 */
interface PetRepository {
    fun getPets(): Flow<List<Pet>>
    suspend fun syncPets(): DomainResult<Unit>
}
