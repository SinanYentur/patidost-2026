package com.patidost.app.data.repository

import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.entity.PetEntity
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * PetRepository Implementation (Offline-First) - 2026 Standard.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
@Singleton
class PetRepositoryImpl @Inject constructor(
    private val petDao: PetDao,
    private val firestore: FirebaseFirestore
) : PetRepository {

    private val petsCollection = firestore.collection("pets")

    override fun getPetsByOwner(ownerId: String): Flow<List<Pet>> {
        return petDao.getPetsByOwner(ownerId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getPetById(petId: String): Flow<Pet?> {
        return petDao.getPetById(petId).map { it?.toDomain() }
    }

    override suspend fun upsertPet(pet: Pet): Result<Unit> = runCatching {
        petDao.upsertPet(PetEntity.fromDomain(pet))
        petsCollection.document(pet.id).set(pet).await()
    }

    override suspend fun deletePet(petId: String): Result<Unit> = runCatching {
        petDao.deletePet(petId)
        petsCollection.document(petId).delete().await()
    }

    override suspend fun adoptPet(petId: String, userId: String): Result<Unit> = runCatching {
        val petDoc = petsCollection.document(petId)
        
        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(petDoc)
            val currentOwner = snapshot.getString("ownerId") ?: ""
            
            if (currentOwner.isEmpty()) {
                transaction.update(petDoc, "ownerId", userId)
            } else {
                throw Exception("Bu hayvan zaten sahiplenilmiş.")
            }
        }.await()

        val updatedPetSnapshot = petDoc.get().await()
        val updatedPet = updatedPetSnapshot.toObject(Pet::class.java)
        updatedPet?.let {
            petDao.upsertPet(PetEntity.fromDomain(it))
        }
    }

    override suspend fun refreshPets(ownerId: String): Result<Unit> = runCatching {
        val snapshot = petsCollection.whereEqualTo("ownerId", ownerId).get().await()
        val remotePets = snapshot.documents.mapNotNull { doc ->
            doc.toObject(Pet::class.java)
        }
        
        remotePets.forEach { pet ->
            petDao.upsertPet(PetEntity.fromDomain(pet))
        }
    }
}
