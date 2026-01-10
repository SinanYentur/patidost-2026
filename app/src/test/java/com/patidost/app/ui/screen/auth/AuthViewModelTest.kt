package com.patidost.app.ui.screen.auth

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.patidost.app.domain.model.User
import com.patidost.app.domain.model.valueobject.EmailVO
import com.patidost.app.domain.model.valueobject.PasswordVO
import com.patidost.app.domain.usecase.auth.SignInUseCase
import com.patidost.app.domain.usecase.auth.SignUpUseCase
import com.patidost.app.domain.util.AppError
import com.patidost.app.domain.util.DomainResult
import io.mockk.coEvery
import io.mockk.eq
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * 🛡️ AuthViewModelTest - V10011.70165 Final Logic Seal.
 * Rule 310: Forcing physical file resync to include MockK 'eq' import.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var signInUseCase: SignInUseCase
    private lateinit var signUpUseCase: SignUpUseCase
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        signInUseCase = mockk()
        signUpUseCase = mockk()
        viewModel = AuthViewModel(signInUseCase, signUpUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Idle`() = runTest {
        assertThat(viewModel.authState.value).isEqualTo(AuthState.Idle)
    }

    @Test
    fun `signIn transitions to Success when successful`() = runTest(testDispatcher) {
        // Given
        val email = "test@patidost.com"
        val password = "pass123"
        val user = User(id = "123", name = "Test User", email = email)
        coEvery { signInUseCase(eq(EmailVO(email)), eq(PasswordVO(password))) } returns DomainResult.Success(user)

        viewModel.authState.test {
            assertThat(awaitItem()).isEqualTo(AuthState.Idle)
            viewModel.signIn(email, password)
            assertThat(awaitItem()).isEqualTo(AuthState.Loading)
            val finalState = awaitItem()
            assertThat(finalState).isInstanceOf(AuthState.Success::class.java)
            assertThat((finalState as AuthState.Success).userName).isEqualTo("Test User")
        }
    }

    @Test
    fun `signUp transitions to Success when successful`() = runTest(testDispatcher) {
        // Given
        val email = "new@patidost.com"
        val password = "pass456"
        val name = "New User"
        val user = User(id = "456", name = name, email = email)
        coEvery { signUpUseCase(eq(EmailVO(email)), eq(PasswordVO(password)), eq(name)) } returns DomainResult.Success(user)

        viewModel.authState.test {
            assertThat(awaitItem()).isEqualTo(AuthState.Idle)
            viewModel.signUp(email, password, name)
            assertThat(awaitItem()).isEqualTo(AuthState.Loading)
            val finalState = awaitItem()
            assertThat(finalState).isInstanceOf(AuthState.Success::class.java)
            assertThat((finalState as AuthState.Success).userName).isEqualTo(name)
        }
    }

    @Test
    fun `signUp transitions to Error when registration fails`() = runTest(testDispatcher) {
        // Given
        val email = "new@patidost.com"
        val password = "pass456"
        val name = "New User"
        val error = AppError.NetworkError("Registration failed")
        coEvery { signUpUseCase(eq(EmailVO(email)), eq(PasswordVO(password)), eq(name)) } returns DomainResult.Error(error)

        viewModel.authState.test {
            assertThat(awaitItem()).isEqualTo(AuthState.Idle)
            viewModel.signUp(email, password, name)
            assertThat(awaitItem()).isEqualTo(AuthState.Loading)
            val finalState = awaitItem()
            assertThat(finalState).isInstanceOf(AuthState.Error::class.java)
            assertThat((finalState as AuthState.Error).message).contains("Registration failed")
        }
    }
}
