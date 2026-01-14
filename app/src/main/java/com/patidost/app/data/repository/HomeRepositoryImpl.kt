package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.remote.ApiService
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.domain.repository.PetFilter
import com.patidost.app.presentation.ui.screen.home.TopGiver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val petDao: PetDao,
    // private val apiService: ApiService // ApiService will be added in a future step
) : HomeRepository {

    // This is a temporary implementation until ApiService is integrated
    override suspend fun getTopGivers(): Resource<List<TopGiver>> {
        return Resource.Success(emptyList())
    }

    override suspend fun getFeaturedPet(): Resource<Pet?> {
        // This will be replaced with the offline-first implementation
        return Resource.Success(null)
    }

    override suspend fun getPetById(id: String): Resource<Pet> {
        TODO("Not yet implemented")
    }

    override suspend fun searchPets(query: String, filter: PetFilter): Resource<List<Pet>> {
        TODO("Not yet implemented")
    }

    override suspend fun givePatiPoint(petId: String, amount: Int): Resource<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPoints(): Resource<Int> {
        TODO("Not yet implemented")
    }
}
