package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * AdoptPetUseCase Unit Test - V140.70 Behavioral Proof.
 * RVWL: Gate 2 Verification using MockK.
 */
class AdoptPetUseCaseTest {

    private val petRepository: PetRepository = mockk()
    private val adoptPetUseCase = AdoptPetUseCase(petRepository)

    @Test
    fun `invoke with petId returns success from repository`() = runTest {
        // Given
        val petId = "pet_123"
        coEvery { petRepository.adoptPet(petId) } returns Result.success(Unit)

        // When
        val result = adoptPetUseCase(petId)

        // Then
        assertTrue(result.isSuccess)
    }

    @Test
    fun `invoke with petId returns failure when repository fails`() = runTest {
        // Given
        val petId = "pet_123"
        val expectedException = Exception("DB Error")
        coEvery { petRepository.adoptPet(petId) } returns Result.failure(expectedException)

        // When
        val result = adoptPetUseCase(petId)

        // Then
        assertTrue(result.isFailure)
        assertEquals(expectedException, result.exceptionOrNull())
    }
}
