package com.patidost.app.data.repository

import com.google.common.truth.Truth.assertThat
import com.patidost.app.core.util.Resource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FakeFriendRepositoryTest {

    private lateinit var repository: FakeFriendRepository

    @Before
    fun setUp() {
        repository = FakeFriendRepository()
    }

    @Test
    fun `getFriends returns success with mock data`() = runTest {
        // Act
        val result = repository.getFriends().first { it !is Resource.Loading } // Skip loading state

        // Assert
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat(result.data).hasSize(2)
        assertThat(result.data?.first()?.name).isEqualTo("Arda")
    }

    @Test
    fun `blockUser and check if it affects future calls`() = runTest {
        // This is a placeholder for a more complex test.
        // In a real scenario, blockUser might not directly affect getFriends,
        // but this shows the test setup is working.

        // Act
        val blockResult = repository.blockUser("some_user_id")

        // Assert
        assertThat(blockResult).isInstanceOf(Resource.Success::class.java)
    }
}
