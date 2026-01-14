package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * The constitutional contract for all user blocking operations.
 */
interface BlockRepository {

    /**
     * Observes the list of users blocked by the current user.
     */
    fun getBlockedUsers(): Flow<Resource<List<User>>>

    /**
     * Blocks a user.
     */
    suspend fun blockUser(userId: String): Resource<Unit>

    /**
     * Unblocks a user.
     */
    suspend fun unblockUser(userId: String): Resource<Unit>

}
