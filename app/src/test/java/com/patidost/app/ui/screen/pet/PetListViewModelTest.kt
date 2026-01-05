package com.patidost.app.ui.screen.pet

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.ui.screen.pet.list.PetListViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PetListViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val petRepository: PetRepository = mockk()
    private val petsFlow = flowOf(listOf(Pet(id = "1", name = "Pati")))

    private lateinit var viewModel: PetListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { petRepository.getPets() } returns petsFlow
        // 🛡️ Fix: Mock syncPets to avoid crash in init block
        coEvery { petRepository.syncPets() } returns Result.success(Unit)
        
        viewModel = PetListViewModel(
            petRepository,
            SavedStateHandle()
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit pets from repository`() = runTest {
        viewModel.uiState.test {
            // UnconfinedTestDispatcher ile stateIn bazen initialValue'yu atlayıp direkt ilk sonucu verebilir.
            // Bu yüzden state'in içeriğini kontrol etmek daha garantidir.
            val state = awaitItem()
            if (state.isLoading) {
                val nextState = awaitItem()
                assertThat(nextState.pets).hasSize(1)
            } else {
                assertThat(state.pets).hasSize(1)
            }
        }
    }
}
