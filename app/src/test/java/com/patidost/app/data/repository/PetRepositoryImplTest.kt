package com.patidost.app.data.repository

import app.cash.turbine.test
import com.google.android.gms.tasks.Tasks
import com.google.common.truth.Truth.assertThat
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.patidost.app.data.local.dao.PetDao
import com.patidost.app.data.local.entity.PetEntity
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.SyncStatus
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * PetRepository Unit Test - V2026.GLOBAL.SEALED
 * Rule 100 Enforced: Physical evidence based alignment with Pet.kt V10000.24000
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PetRepositoryImplTest {

    private lateinit var repository: PetRepositoryImpl
    private val petDao: PetDao = mockk(relaxed = true)
    private val firestore: FirebaseFirestore = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        // Correct signature: PetRepositoryImpl(FirebaseFirestore, PetDao, CoroutineDispatcher)
        repository = PetRepositoryImpl(firestore, petDao, testDispatcher)
    }

    @Test
    fun `getPetsByOwner should return mapped domain pets from DAO`() = runTest(testDispatcher) {
        val ownerId = "owner_123"
        val petEntities = listOf(
            PetEntity(
                id = "1", 
                name = "Pati", 
                breed = "Golden",
                species = "Dog", 
                age = 2, 
                price = 0.0,
                ownerId = ownerId,
                imageUrl = "url", 
                videoUrl = null,
                description = "Happy", 
                rescueStory = "Saved",
                personalityTraits = "Loyal",
                isAdopted = false,
                lastUpdated = 0L,
                syncStatus = SyncStatus.SYNCED.name
            )
        )
        every { petDao.getPetsByOwner(ownerId) } returns flowOf(petEntities)

        repository.getPetsByOwner(ownerId).test {
            val result = awaitItem()
            assertThat(result).hasSize(1)
            assertThat(result[0].name).isEqualTo("Pati")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `upsertPet should complete successfully`() = runTest(testDispatcher) {
        // Given
        val pet = Pet(
            id = "1", 
            name = "Pati", 
            breed = "Golden",
            species = "Dog", 
            age = 2, 
            price = 0.0,
            ownerId = "owner_123",
            imageUrl = "url", 
            videoUrl = null,
            description = "Happy",
            rescueStory = "Saved",
            personalityTraits = listOf("Loyal"),
            isAdopted = false,
            lastUpdated = 0L,
            syncStatus = SyncStatus.SYNCED
        )
        
        val mockDoc = mockk<DocumentReference>()
        val mockColl = mockk<CollectionReference>()

        every { firestore.collection("pets") } returns mockColl
        every { mockColl.document(pet.id) } returns mockDoc
        every { mockDoc.set(any()) } returns Tasks.forResult(null)
        
        coEvery { petDao.insertPet(any()) } returns Unit

        // When
        val result = repository.upsertPet(pet)

        // Then
        assertThat(result.isSuccess).isTrue()
        coVerify(exactly = 1) { petDao.insertPet(any()) }
    }
}
