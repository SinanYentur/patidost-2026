
package com.patidost.app.presentation.ui.screen.detail

import app.cash.turbine.test
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.usecase.GivePatiPointUseCase
import com.patidost.app.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PatiPointViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var givePatiPointUseCase: GivePatiPointUseCase
    private lateinit var viewModel: PatiPointViewModel

    @Before
    fun setUp() {
        givePatiPointUseCase = mockk()
        viewModel = PatiPointViewModel(givePatiPointUseCase)
    }

    @Test
    fun `givePatiPoints success should emit ShowDonationSuccess event`() = runTest {
        // Arrange
        val user = User(uid = "1", name = "Test", email = "a@b.com", avatarUrl = "", patiPoints = 50)
        coEvery { givePatiPointUseCase(any(), any()) } returns Resource.Success(user.copy(patiPoints = 0))

        viewModel.eventFlow.test {
            // Act
            viewModel.givePatiPoints("pet1", 50)

            // Assert
            val event = awaitItem()
            assertTrue(event is PatiPointEvent.ShowDonationSuccess)
            val successMessage = (event as PatiPointEvent.ShowDonationSuccess).message
            assertEquals("Başarıyla 50 Pati Puanı gönderildi! Yeni bakiyen: 0", successMessage)
        }
    }
}
