package com.patidost.app.data.repository

import com.google.common.truth.Truth.assertThat
import com.patidost.app.core.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
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
        val results = mutableListOf<Resource<List<com.patidost.app.domain.model.User>>>()
        val job = launch { repository.getBlockedUsers().collect { results.add(it) } }
        delay(400) // allow flow to emit

        val successResult = results.firstOrNull { it is Resource.Success } as? Resource.Success
        assertThat(successResult?.data).isNotNull()
        assertThat(successResult?.data).isEmpty()
        job.cancel()
    }

    @Test
    fun `blockUser adds user to the list`() = runTest {
        // Arrange
        val userIdToBlock = "user_to_block"

        // Act
        repository.blockUser(userIdToBlock)
        val results = mutableListOf<Resource<List<com.patidost.app.domain.model.User>>>()
        val job = launch { repository.getBlockedUsers().collect { results.add(it) } }
        delay(400)

        // Assert
        val successResult = results.firstOrNull { it is Resource.Success } as? Resource.Success
        assertThat(successResult?.data).hasSize(1)
        assertThat(successResult?.data?.first()?.uid).isEqualTo(userIdToBlock)
        job.cancel()
    }

    @Test
    fun `unblockUser removes user from the list`() = runTest {
        // Arrange
        val userIdToBlock = "user_to_block"
        repository.blockUser(userIdToBlock)

        // Act
        repository.unblockUser(userIdToBlock)
        val results = mutableListOf<Resource<List<com.patidost.app.domain.model.User>>>()
        val job = launch { repository.getBlockedUsers().collect { results.add(it) } }
        delay(400)
        
        // Assert
        val successResult = results.firstOrNull { it is Resource.Success } as? Resource.Success
        assertThat(successResult?.data).isNotNull()
        assertThat(successResult?.data).isEmpty()
        job.cancel()
    }
}
