package com.patidost.app.presentation.ui.screen.block

import com.google.common.truth.Truth.assertThat
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.BlockRepository
import com.patidost.app.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BlockViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: BlockViewModel
    private lateinit var blockRepository: BlockRepository

    @Before
    fun setUp() {
        blockRepository = mockk()
    }

    @Test
    fun `loadBlockedUsers on init, state is updated to success`() {
        // Arrange
        val fakeBlockedUsers = listOf(User("1", "Blocked User 1", "", "", 0, "", 0))
        coEvery { blockRepository.getBlockedUsers() } returns flowOf(Resource.Success(fakeBlockedUsers))

        // Act
        viewModel = BlockViewModel(blockRepository)

        // Assert
        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.blockedUsers).isEqualTo(fakeBlockedUsers)
        assertThat(state.error).isNull()
    }

    @Test
    fun `loadBlockedUsers on init with error, state is updated to error`() {
        // Arrange
        val errorMessage = "Could not load blocked users"
        coEvery { blockRepository.getBlockedUsers() } returns flowOf(Resource.Error(errorMessage))

        // Act
        viewModel = BlockViewModel(blockRepository)

        // Assert
        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.blockedUsers).isEmpty()
        assertThat(state.error).isEqualTo(errorMessage)
    }
}
