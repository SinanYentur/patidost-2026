package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * SignInUseCase Unit Test - V140.75 Behavioral Proof.
 * RVWL: Switched to MockK for constitutional compliance.
 */
class SignInUseCaseTest {

    private val authRepository: AuthRepository = mockk()
    private val signInUseCase = SignInUseCase(authRepository)

    @Test
    fun `invoke returns success when repository sign in succeeds`() = runTest {
        // Given
        val email = "test@patidost.com"
        val password = "password123"
        val user = User("1", email, "Test User")
        coEvery { authRepository.signIn(email, password) } returns Result.success(user)

        // When
        val result = signInUseCase(email, password)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(user, result.getOrNull())
    }

    @Test
    fun `invoke returns failure when repository sign in fails`() = runTest {
        // Given
        val email = "test@patidost.com"
        val password = "wrong_password"
        val exception = Exception("Auth Failed")
        coEvery { authRepository.signIn(email, password) } returns Result.failure(exception)

        // When
        val result = signInUseCase(email, password)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
