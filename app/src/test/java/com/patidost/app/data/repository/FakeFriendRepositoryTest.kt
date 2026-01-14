package com.patidost.app.data.repository

import com.google.common.truth.Truth.assertThat
import com.patidost.app.core.util.Resource
import kotlinx.coroutines.flow.drop
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
        // Drop the initial Loading state and get the first real emission (Success)
        val result = repository.getFriends().drop(1).first()

        // Assert
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        val successResult = result as Resource.Success
        assertThat(successResult.data).isNotNull()
        assertThat(successResult.data).hasSize(2)
        assertThat(successResult.data?.first()?.name).isEqualTo("Arda")
    }

    @Test
    fun `sendFriendRequest should return success`() = runTest {
        // Act
        val result = repository.sendFriendRequest("some_user_id")

        // Assert
        assertThat(result).isInstanceOf(Resource.Success::class.java)
    }
}
