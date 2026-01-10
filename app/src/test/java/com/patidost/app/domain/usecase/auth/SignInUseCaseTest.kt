package com.patidost.app.domain.usecase.auth

import com.google.common.truth.Truth.assertThat
import com.patidost.app.domain.model.User
import com.patidost.app.domain.model.valueobject.EmailVO
import com.patidost.app.domain.model.valueobject.PasswordVO
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import io.mockk.coEvery
import io.mockk.eq
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * 🛡️ SignInUseCaseTest - V10011.70165 Logic Seal.
 * Rule 310: Forcing physical file resync to include MockK 'eq' import.
 */
class SignInUseCaseTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var signInUseCase: SignInUseCase
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        authRepository = mockk(relaxed = true)
        signInUseCase = SignInUseCase(authRepository, testDispatcher)
    }

    @Test
    fun `invoke with valid credentials returns success`() = runTest {
        // Given
        val email = "test@patidost.com"
        val password = "Sifre123!"
        val expectedUser = User("1", "Test User", email)
        coEvery { authRepository.signIn(eq(email), eq(password)) } returns DomainResult.Success(expectedUser)

        // When
        val result = signInUseCase(EmailVO(email), PasswordVO(password))

        // Then
        assertThat(result).isInstanceOf(DomainResult.Success::class.java)
        val user = (result as DomainResult.Success).data
        assertThat(user).isEqualTo(expectedUser)
    }
}
