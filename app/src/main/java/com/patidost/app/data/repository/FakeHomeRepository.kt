package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.PetOwner
import com.patidost.app.domain.model.TopGiver
import com.patidost.app.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHomeRepository : HomeRepository {

    private val fakePets = listOf(
        Pet(
            id = "1", name = "Mırmır", breed = "Tekir", imageUrl = "", age = 3, patiPoints = 100,
            owner = PetOwner(ownerId = "user1", ownerName = "Ayşe", ownerAvatarUrl = "")
        ),
        Pet(
            id = "2", name = "Paşa", breed = "Sivas Kangalı", imageUrl = "", age = 5, patiPoints = 250,
            owner = PetOwner(ownerId = "user2", ownerName = "Ahmet", ownerAvatarUrl = "")
        )
    )

    override fun getFeaturedPets(): Flow<Resource<List<Pet>>> = flow {
        emit(Resource.Success(fakePets))
    }

    override fun getTopGivers(): Flow<Resource<List<TopGiver>>> = flow {
        val fakeGivers = listOf(
            TopGiver("user2", "Ahmet", "", 50),
            TopGiver("user1", "Ayşe", "", 30)
        )
        emit(Resource.Success(fakeGivers))
    }
}
