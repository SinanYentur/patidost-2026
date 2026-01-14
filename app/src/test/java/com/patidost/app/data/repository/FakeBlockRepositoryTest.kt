package com.patidost.app.data.repository

import com.google.common.truth.Truth.assertThat
import com.patidost.app.core.util.Resource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FakeBlockRepositoryTest {

    private lateinit var repository: FakeBlockRepository

    @Before
    fun setUp() {
        repository = FakeBlockRepository()
    }

    @Test
    fun `initially blocked users list is empty`() = runTest {
        val result = repository.getBlockedUsers().first { it !is Resource.Loading }
        assertThat(result.data).isEmpty()
    }

    @Test
    fun `blockUser adds user to the list`() = runTest {
        // Arrange
        val userIdToBlock = "user_to_block"

        // Act
        repository.blockUser(userIdToBlock)
        val result = repository.getBlockedUsers().first { it !is Resource.Loading }

        // Assert
        assertThat(result.data).hasSize(1)
        assertThat(result.data?.first()?.uid).isEqualTo(userIdToBlock)
    }

    @Test
    fun `unblockUser removes user from the list`() = runTest {
        // Arrange
        val userIdToBlock = "user_to_block"
        repository.blockUser(userIdToBlock)

        // Act
        repository.unblockUser(userIdToBlock)
        val result = repository.getBlockedUsers().first { it !is Resource.Loading }

        // Assert
        assertThat(result.data).isEmpty()
    }
}
