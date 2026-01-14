
package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.presentation.ui.util.UiText
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GivePatiPointUseCaseTest {

    private lateinit var homeRepository: HomeRepository
    private lateinit var authRepository: AuthRepository
    private lateinit var givePatiPointUseCase: GivePatiPointUseCase

    @Before
    fun setUp() {
        homeRepository = mockk(relaxed = true)
        authRepository = mockk(relaxed = true)
        givePatiPointUseCase = GivePatiPointUseCase(homeRepository, authRepository)
    }

    @Test
    fun `invoke with insufficient funds should return error and not call homeRepository`() = runBlocking {
        // Arrange
        val userWithLowBalance = User(uid = "123", email = "test@test.com", patiPoints = 10)
        coEvery { authRepository.getCurrentUser() } returns Resource.Success(userWithLowBalance)

        // Act
        val result = givePatiPointUseCase("pet1", 50)

        // Assert
        assertTrue(result is Resource.Error)
        assertEquals("Yetersiz Pati Puanı.", (result.message as UiText.DynamicString).value)

        // Verify that homeRepository.givePatiPoint was never called
        coVerify(exactly = 0) { homeRepository.givePatiPoint(any(), any()) }
    }
}
