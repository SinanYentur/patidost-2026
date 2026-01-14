package com.patidost.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.core.util.Resource
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.mapper.toDto
import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.PetOwner
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : PetRepository {

    override fun getOwnedPets(userId: String): Flow<List<Pet>> = flow {
        val petIdsSnapshot = firestore.collection("users").document(userId).collection("pets").get().await()
        val petIds = petIdsSnapshot.documents.map { it.id }

        if (petIds.isEmpty()) {
            emit(emptyList())
            return@flow
        }

        val owner = getOwner(userId)
        val petsSnapshot = firestore.collection("pets").whereIn("__name__", petIds).get().await()
        val pets = petsSnapshot.toObjects(PetDto::class.java).mapIndexed { index, petDto ->
            petDto.id = petsSnapshot.documents[index].id
            petDto.toDomain(owner)
        }
        emit(pets)
    }

    override suspend fun getPetProfile(petId: String): Pet? {
        val petDoc = firestore.collection("pets").document(petId).get().await()
        val petDto = petDoc.toObject(PetDto::class.java) ?: return null
        petDto.id = petDoc.id

        val owner = getOwner(petDto.ownerId)
        return petDto.toDomain(owner)
    }

    override suspend fun addPet(pet: Pet): Resource<Unit> {
        return try {
            val petDto = pet.toDto()
            firestore.collection("pets").add(petDto).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(UiText.DynamicString(e.message ?: "An unknown error occurred."))
        }
    }

    private suspend fun getOwner(userId: String): PetOwner {
        val userDoc = firestore.collection("users").document(userId).get().await()
        val userName = userDoc.getString("name") ?: ""
        val userAvatar = userDoc.getString("photoUrl")
        return PetOwner(ownerId = userId, ownerName = userName, ownerAvatarUrl = userAvatar)
    }
}
