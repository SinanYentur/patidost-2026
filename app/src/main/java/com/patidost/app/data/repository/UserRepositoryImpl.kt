package com.patidost.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.remote.dto.UserDto
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {

    override suspend fun getUser(userId: String, forceRefresh: Boolean): Resource<User> {
        return try {
            val document = firestore.collection("users").document(userId).get().await()
            val userDto = document.toObject(UserDto::class.java)
            if (userDto != null) {
                Resource.Success(userDto.toDomain())
            } else {
                Resource.Error(UiText.DynamicString("User not found."))
            }
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred."))
        }
    }

    override fun getPetsForUser(userId: String): Flow<Resource<List<Pet>>> {
        // TODO: Implement this reactively with callbackFlow similar to ConversationRepository
        return flow { emit(Resource.Error(UiText.DynamicString("Not implemented"))) }
    }

    override suspend fun addPet(pet: Pet): Resource<Unit> {
        // TODO: Implement this with a firestore.collection("pets").add(petDto) call
        return Resource.Error(UiText.DynamicString("Not implemented"))
    }

    override suspend fun updateUser(user: User): Resource<Unit> {
        // TODO: Implement this with a firestore.collection("users").document(user.uid).set(userDto) call
        return Resource.Error(UiText.DynamicString("Not implemented"))
    }
}
