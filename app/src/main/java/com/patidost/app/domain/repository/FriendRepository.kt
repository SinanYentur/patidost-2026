package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * The constitutional contract for all friendship-related data operations.
 */
interface FriendRepository {

    /**
     * Observes the list of friends for the current user.
     */
    fun getFriends(): Flow<Resource<List<User>>>

    /**
     * Sends a friend request to another user.
     */
    suspend fun sendFriendRequest(userId: String): Resource<Unit>

    /**
     * Accepts a friend request from another user.
     */
    suspend fun acceptFriendRequest(userId: String): Resource<Unit>

    /**
     * Rejects a friend request from another user.
     */
    suspend fun rejectFriendRequest(userId: String): Resource<Unit>

    /**
     * Removes a user from the friend list.
     */
    suspend fun removeFriend(userId: String): Resource<Unit>
}
