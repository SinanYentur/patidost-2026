package com.patidost.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.remote.HomeDataSource
import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.PetOwner
import com.patidost.app.domain.model.TopGiver
import com.patidost.app.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
    private val firestore: FirebaseFirestore // Added dependency
) : HomeRepository {

    override fun getFeaturedPets(): Flow<Resource<List<Pet>>> = flow {
        homeDataSource.getFeaturedPets().collect { resource ->
            when (resource) {
                is Resource.Loading -> emit(Resource.Loading())
                is Resource.Success -> {
                    val petDtos = resource.data ?: emptyList()
                    // Fetch all owners in a single batch for efficiency if possible, 
                    // but for simplicity here we fetch one by one.
                    val pets = petDtos.mapNotNull { petDto ->
                        getOwner(petDto.ownerId)?.let { owner ->
                            petDto.toDomain(owner)
                        }
                    }
                    emit(Resource.Success(pets))
                }
                is Resource.Error -> emit(Resource.Error(resource.message))
            }
        }
    }

    override fun getTopGivers(): Flow<Resource<List<TopGiver>>> {
        return homeDataSource.getTopGivers()
    }

    private suspend fun getOwner(userId: String): PetOwner? {
        return try {
            val userDoc = firestore.collection("users").document(userId).get().await()
            val userName = userDoc.getString("name") ?: ""
            val userAvatar = userDoc.getString("photoUrl")
            PetOwner(ownerId = userId, ownerName = userName, ownerAvatarUrl = userAvatar)
        } catch (e: Exception) {
            null
        }
    }
}
