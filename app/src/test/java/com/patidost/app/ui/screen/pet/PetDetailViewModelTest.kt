package com.patidost.app.ui.screen.pet

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.ui.screen.pet.detail.PetDetailUiState
import com.patidost.app.ui.screen.pet.detail.PetDetailViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PetDetailViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val petRepository: PetRepository = mockk()
    
    // 🛡️ Fix: Initialize with a default value to prevent immediate error state in init block
    private val petFlow = MutableStateFlow<Pet?>(Pet(id = "pet_123"))
    private val petId = "pet_123"

    private lateinit var viewModel: PetDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { petRepository.getPetById(petId) } returns petFlow
        
        viewModel = PetDetailViewModel(
            petRepository,
            SavedStateHandle(mapOf("petId" to petId))
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit Success when pet details are fetched`() = runTest {
        viewModel.uiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(PetDetailUiState.Success::class.java)
            assertThat((state as PetDetailUiState.Success).pet.id).isEqualTo(petId)
        }
    }
}
