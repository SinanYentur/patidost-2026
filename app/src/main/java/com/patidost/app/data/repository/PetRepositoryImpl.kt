package com.patidost.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.entity.PetEntity
import com.patidost.app.di.IoDispatcher
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PetRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val petDao: PetDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PetRepository {

    override fun getPets(): Flow<List<Pet>> = petDao.getAllPets()
        .map { entities -> entities.map { it.toDomain() } }
        .onStart { syncPets() }

    override fun getPetById(id: String): Flow<Pet?> = petDao.getPetById(id)
        .map { it?.toDomain() }

    override suspend fun syncPets(): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            val snapshot = firestore.collection("pets")
                .whereEqualTo("isAdopted", false)
                .limit(100)
                .get()
                .await()
            val pets = snapshot.toObjects(Pet::class.java)
            if (pets.isNotEmpty()) {
                petDao.insertPets(pets.map { PetEntity.fromDomain(it) })
            }
            Unit
        }
    }

    override suspend fun adoptPet(petId: String): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            firestore.collection("pets").document(petId).update("isAdopted", true).await()
            val localPet = petDao.getPetByIdDirect(petId)
            localPet?.let {
                petDao.insertPet(it.copy(isAdopted = true))
            }
            Unit
        }
    }

    override suspend fun adoptPets(petIds: List<String>, userId: String): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            firestore.runTransaction { transaction ->
                petIds.forEach { id ->
                    val ref = firestore.collection("pets").document(id)
                    transaction.update(ref, "isAdopted", true, "ownerId", userId)
                }
            }.await()
            petIds.forEach { id ->
                val localPet = petDao.getPetByIdDirect(id)
                localPet?.let {
                    petDao.insertPet(it.copy(isAdopted = true, ownerId = userId))
                }
            }
            Unit
        }
    }

    override suspend fun searchPets(query: String): Result<List<Pet>> = withContext(ioDispatcher) {
        runCatching {
            val snapshot = firestore.collection("pets")
                .whereGreaterThanOrEqualTo("name", query)
                .whereLessThanOrEqualTo("name", query + "\uf8ff")
                .get()
                .await()
            snapshot.toObjects(Pet::class.java)
        }
    }

    override suspend fun upsertPet(pet: Pet): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            firestore.collection("pets").document(pet.id).set(pet).await()
            petDao.insertPet(PetEntity.fromDomain(pet))
            Unit
        }
    }

    override suspend fun deletePet(petId: String): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            firestore.collection("pets").document(petId).delete().await()
            petDao.deletePetById(petId)
            Unit
        }
    }

    override fun getPetsByOwner(ownerId: String): Flow<List<Pet>> = petDao.getPetsByOwner(ownerId)
        .map { entities -> entities.map { it.toDomain() } }
}
