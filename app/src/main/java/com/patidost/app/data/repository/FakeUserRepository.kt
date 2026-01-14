package com.patidost.app.data.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserRepository : UserRepository {

    private val pets = mutableListOf<Pet>()
    private var user: User? = null

    override suspend fun getUser(userId: String, forceRefresh: Boolean): Resource<User> {
        return user?.let { Resource.Success(it) } ?: Resource.Error(UiText.DynamicString("User not found"))
    }

    override fun getPetsForUser(userId: String): Flow<Resource<List<Pet>>> {
        // Simulate returning an empty list of pets to prevent the app from crashing.
        // This satisfies the HomeViewModel's requirement.
        return flowOf(Resource.Success(emptyList()))
    }

    override suspend fun addPet(pet: Pet): Resource<Unit> {
        pets.add(pet)
        return Resource.Success(Unit)
    }

    override suspend fun updateUser(user: User): Resource<Unit> {
        this.user = user
        return Resource.Success(Unit)
    }
}
