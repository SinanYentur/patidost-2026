package com.patidost.app.ui.screen.auth

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import io.mockk.coEvery
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
 * AuthViewModel Unit Test - V45.89 Timing Integrity Fix.
 * RVWL: Using StandardTestDispatcher for precise state transition control.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private val userRepository: UserRepository = mockk()

    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AuthViewModel(userRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Idle`() = runTest {
        viewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(AuthUiState.Idle)
        }
    }

    @Test
    fun `onAuthAction should transition to Authenticated when successful`() = runTest(testDispatcher) {
        // Given
        val user = User(id = "123", name = "Test User", email = "test@pati.com")
        coEvery { userRepository.signIn(any(), any()) } returns Result.success(user)

        viewModel.uiState.test {
            // Initial state
            assertThat(awaitItem()).isEqualTo(AuthUiState.Idle)

            // When
            viewModel.onAuthAction("test@email.com", "pass123", null)

            // Then - Check Loading
            assertThat(awaitItem()).isEqualTo(AuthUiState.Loading)
            
            // Allow coroutines to complete
            testDispatcher.scheduler.advanceUntilIdle()

            // Check Authenticated
            assertThat(awaitItem()).isEqualTo(AuthUiState.Authenticated)
        }
    }
}
