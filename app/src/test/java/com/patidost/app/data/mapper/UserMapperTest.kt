package com.patidost.app.data.mapper

import com.google.common.truth.Truth.assertThat
import com.patidost.app.data.remote.dto.UserDto
import org.junit.Test

class UserMapperTest {

    @Test
    fun `UserDto with all fields maps correctly to User`() {
        // Arrange
        val userDto = UserDto(
            id = "user123",
            email = "test@patidost.com",
            name = "Test User",
            status = "active",
            verificationLevel = 2,
            avatarUrl = "http://example.com/avatar.jpg",
            patiPoints = 100
        )

        // Act
        val user = userDto.toDomain()

        // Assert
        assertThat(user.uid).isEqualTo("user123")
        assertThat(user.name).isEqualTo("Test User")
        assertThat(user.email).isEqualTo("test@patidost.com")
        assertThat(user.avatarUrl).isEqualTo("http://example.com/avatar.jpg")
        assertThat(user.patiPoints).isEqualTo(100)
        assertThat(user.status).isEqualTo("active")
        assertThat(user.verificationLevel).isEqualTo(2)
    }

    @Test
    fun `UserDto with null fields maps correctly with default values`() {
        // Arrange
        val userDto = UserDto(
            id = "user456",
            email = "nulltest@patidost.com",
            name = "Null Test",
            status = "pending",
            verificationLevel = 1,
            avatarUrl = null,
            patiPoints = null
        )

        // Act
        val user = userDto.toDomain()

        // Assert
        assertThat(user.uid).isEqualTo("user456")
        assertThat(user.name).isEqualTo("Null Test")
        assertThat(user.avatarUrl).isEqualTo("") // Null should map to empty string
        assertThat(user.patiPoints).isEqualTo(0)    // Null should map to 0
    }
}
