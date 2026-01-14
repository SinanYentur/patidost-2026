package com.patidost.app.data.remote

import com.patidost.app.core.util.Resource
import com.patidost.app.data.remote.dto.UserDto

/**
 * Interface for user data source.
 * Defines the contract for user profile data operations in Firestore.
 */
interface UserDataSource {
    suspend fun createUser(userDto: UserDto): Resource<Unit>
    suspend fun getUser(userId: String): Resource<UserDto?>
}
