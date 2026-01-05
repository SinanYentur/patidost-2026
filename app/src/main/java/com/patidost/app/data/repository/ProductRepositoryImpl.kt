package com.patidost.app.data.repository

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.util.DomainResult
import com.patidost.app.domain.util.AppError
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.remote.PetRemoteDataSource
import com.patidost.app.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 🛡️ HUBX 13/13 ENTERPRISE DATA LAYER
 * Rule: Mutex concurrency guard + Granular Error Mapping.
 */
@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val petDao: PetDao,
    private val remoteDataSource: PetRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PetRepository {

    private val syncMutex = Mutex()

    override fun getPets(): Flow<List<Pet>> = petDao.getAllPets()
        .map { entities -> entities.map { it.toDomain() } }
        .onStart { safeSync() }
        .flowOn(ioDispatcher)

    private suspend fun safeSync() {
        // 🛡️ Rule: Prevent redundant network calls / race conditions
        if (!syncMutex.isLocked) {
            syncPets()
        }
    }

    override suspend fun syncPets(): DomainResult<Unit> = withContext(ioDispatcher) {
        syncMutex.withLock {
            return@withContext try {
                when (val remote = remoteDataSource.fetchPets()) {
                    is DomainResult.Success -> {
                        petDao.refreshPets(remote.data.map { it.toEntity() })
                        DomainResult.Success(Unit)
                    }
                    is DomainResult.Error -> {
                        // 🛡️ Rule: Granular error propagation (No general "NetworkError")
                        DomainResult.Error(remote.error)
                    }
                }
            } catch (e: java.net.SocketTimeoutException) {
                DomainResult.Error(AppError.Network.Timeout)
            } catch (e: Exception) {
                DomainResult.Error(AppError.Unknown(e))
            }
        }
    }

    private fun com.patidost.app.data.local.entity.PetEntity.toDomain() = Pet(id, name, breed, imageUrl, description)
    private fun Pet.toEntity() = com.patidost.app.data.local.entity.PetEntity(id, name, breed, imageUrl, description)
}
