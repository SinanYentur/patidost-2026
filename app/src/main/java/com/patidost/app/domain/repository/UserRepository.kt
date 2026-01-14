package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource

/**
 * Repository for user-specific data operations.
 */
interface UserRepository {

    /**
     * Atomically gives pati points from the current user to a pet.
     * This should be a transactional operation.
     */
    suspend fun givePatiPoints(fromUserId: String, toPetId: String, amount: Int): Resource<Unit>
}
