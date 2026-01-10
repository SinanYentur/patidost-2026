package com.patidost.app.domain.usecase.pet

import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.domain.util.DomainResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

/**
 * 🛡️ AdoptPetUseCaseTest - V10000.70095 Domain Seal.
 */
class AdoptPetUseCaseTest {

    private lateinit var petRepository: PetRepository
    private lateinit var adoptPetUseCase: AdoptPetUseCase

    @Before
    fun setup() {
        petRepository = mockk()
        adoptPetUseCase = AdoptPetUseCase(petRepository)
    }

    @Test
    fun invoke_returnsSuccess() = runTest {
        coEvery { petRepository.adoptPet(any()) } returns DomainResult.Success(Unit)
        
        val result = adoptPetUseCase("1")
        assertThat(result).isInstanceOf(DomainResult.Success::class.java)
    }
}
