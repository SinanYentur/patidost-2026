package com.patidost.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.mapper.toDomain
import com.patidost.app.data.mapper.toEntity
import com.patidost.app.data.model.PetEntity
import com.patidost.app.di.IoDispatcher
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.util.DomainResult
import com.patidost.app.domain.util.AppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 🛡️ PetRepositoryImpl - Sovereign Engine Standard V10000.70014.
 * Rule 420: Fixed Entity pathing, DAO synchronization, and Flow mapping.
 */
@Singleton
class PetRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val petDao: PetDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PetRepository {

    override fun getPets(): Flow<List<Pet>> = petDao.getAllPets()
        .map { entities -> entities.map { it.toDomain() } }

    override fun getPetById(id: String): Flow<Pet?> = flow {
        emit(petDao.getPetById(id)?.toDomain())
    }

    override suspend fun addPet(pet: Pet): DomainResult<Unit> = upsertPet(pet)

    override suspend fun adoptPet(petId: String): DomainResult<Unit> = withContext(ioDispatcher) {
        try {
            firestore.collection("pets").document(petId).update("isAdopted", true).await()
            DomainResult.Success(Unit)
        } catch (e: Exception) {
            DomainResult.Error(AppError.UnknownError(e.message ?: "Unknown error"))
        }
    }

    override suspend fun refreshPets(): DomainResult<Unit> = syncPets()

    override suspend fun deletePet(petId: String): DomainResult<Unit> = withContext(ioDispatcher) {
        try {
            // PetDao uses PetEntity for delete. We fetch it first or create a dummy with ID.
            val entity = petDao.getPetById(petId) ?: PetEntity(id = petId, name = "", breed = "", age = 0, imageUrl = "")
            petDao.deletePet(entity)
            firestore.collection("pets").document(petId).delete().await()
            DomainResult.Success(Unit)
        } catch (e: Exception) {
            DomainResult.Error(AppError.UnknownError(e.message ?: "Unknown error"))
        }
    }

    override suspend fun syncPets(): DomainResult<Unit> = withContext(ioDispatcher) {
        try {
            val snapshot = firestore.collection("pets")
                .whereEqualTo("isAdopted", false)
                .get()
                .await()
            val pets = snapshot.toObjects(Pet::class.java)
            // Rule 300.2: SSOT sync with batch insert logic
            pets.forEach { petDao.insertPet(it.toEntity()) }
            DomainResult.Success(Unit)
        } catch (e: Exception) {
            DomainResult.Error(AppError.UnknownError(e.message ?: "Unknown error"))
        }
    }

    override fun searchPets(query: String): Flow<List<Pet>> = petDao.getAllPets()
        .map { entities -> 
            entities.filter { it.name.contains(query, ignoreCase = true) || it.breed.contains(query, ignoreCase = true) }
                .map { it.toDomain() }
        }

    override suspend fun upsertPet(pet: Pet): DomainResult<Unit> = withContext(ioDispatcher) {
        try {
            petDao.insertPet(pet.toEntity())
            DomainResult.Success(Unit)
        } catch (e: Exception) {
            DomainResult.Error(AppError.UnknownError(e.message ?: "Unknown error"))
        }
    }

    override fun getPetsByOwner(ownerId: String): Flow<List<Pet>> = petDao.getAllPets()
        .map { entities -> 
            entities.filter { it.ownerId == ownerId }.map { it.toDomain() }
        }
}
