package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * GetPetsUseCase Unit Test - V140.65 Behavioral Integrity.
 * RVWL: Switched to MockK per project dependencies (Rule 97).
 */
class GetPetsUseCaseTest {

    private val petRepository: PetRepository = mockk()
    private val getPetsUseCase = GetPetsUseCase(petRepository)

    @Test
    fun `invoke returns sorted pets by name`() = runTest {
        // Given
        val ownerId = "owner_1"
        val pets = listOf(
            Pet("1", "Zebra", "Breed", "Species", 2, 0.0, ownerId, "", ""),
            Pet("2", "Alpha", "Breed", "Species", 1, 0.0, ownerId, "", "")
        )
        every { petRepository.getPetsByOwner(ownerId) } returns flowOf(pets)

        // When
        val result = getPetsUseCase(ownerId).first()

        // Then
        assertEquals("Alpha", result[0].name)
        assertEquals("Zebra", result[1].name)
    }
}
