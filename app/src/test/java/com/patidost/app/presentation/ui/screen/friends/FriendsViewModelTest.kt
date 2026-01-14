package com.patidost.app.presentation.ui.screen.friends

import com.google.common.truth.Truth.assertThat
import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.FriendRepository
import com.patidost.app.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FriendsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: FriendsViewModel
    private lateinit var friendRepository: FriendRepository

    @Before
    fun setUp() {
        friendRepository = mockk()
    }

    @Test
    fun `loadFriends on init, state is updated to success`() {
        // Arrange
        val fakeFriends = listOf(User("1", "Test Friend", "", "", 0, "", 0))
        coEvery { friendRepository.getFriends() } returns flowOf(Resource.Success(fakeFriends))

        // Act
        viewModel = FriendsViewModel(friendRepository)

        // Assert
        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.friends).isEqualTo(fakeFriends)
        assertThat(state.error).isNull()
    }

    @Test
    fun `loadFriends on init with error, state is updated to error`() {
        // Arrange
        val errorMessage = "Could not load friends"
        coEvery { friendRepository.getFriends() } returns flowOf(Resource.Error(errorMessage))

        // Act
        viewModel = FriendsViewModel(friendRepository)

        // Assert
        val state = viewModel.uiState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.friends).isEmpty()
        assertThat(state.error).isEqualTo(errorMessage)
    }
}
