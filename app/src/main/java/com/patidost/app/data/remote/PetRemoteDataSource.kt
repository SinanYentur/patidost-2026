package com.patidost.app.data.remote

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.util.DomainResult

/**
 * 🛡️ PetRemoteDataSource - V49.95 Data Sovereignty Contract.
 * RVWL: Unified interface for remote pet operations.
 */
interface PetRemoteDataSource {
    suspend fun fetchPets(): DomainResult<List<Pet>>
}
