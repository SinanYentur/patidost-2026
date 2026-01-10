package com.patidost.app.ui.screen.pet

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

/**
 * 🛡️ PetDetailViewModelTest - V10000.70105 Logic Seal.
 * Rule 310: Fixed StateFlow observation with Turbine.
 */
class PetDetailViewModelTest {

    private lateinit var viewModel: PetDetailViewModel
    private lateinit var petRepository: PetRepository
    private val savedStateHandle = SavedStateHandle(mapOf("petId" to "1"))

    @Before
    fun setup() {
        petRepository = mockk()
        val pet = Pet(id = "1", name = "Pati", breed = "Kedi", imageUrl = "", description = "")
        every { petRepository.getPetById("1") } returns flowOf(pet)
        
        viewModel = PetDetailViewModel(petRepository, savedStateHandle)
    }

    @Test
    fun uiState_shouldEmitSuccess() = runTest {
        // 🛡️ Mühür: Flow'un Loading -> Success geçişini test et
        viewModel.uiState.test {
            // İlk değer Loading olabilir
            val item = awaitItem()
            if (item is PetDetailUiState.Loading) {
                val successItem = awaitItem()
                assertThat(successItem).isInstanceOf(PetDetailUiState.Success::class.java)
            } else {
                assertThat(item).isInstanceOf(PetDetailUiState.Success::class.java)
            }
        }
    }
}
