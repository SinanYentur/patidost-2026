package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * The constitutional contract for all user-related data operations.
 */
interface UserRepository {

    /**
     * Fetches a user's data. May use a cache or force a network refresh.
     */
    suspend fun getUser(userId: String, forceRefresh: Boolean): Resource<User>

    /**
     * Observes the pets belonging to a specific user for real-time updates.
     */
    fun getPetsForUser(userId: String): Flow<Resource<List<Pet>>>

    /**
     * Adds a new pet for the current user.
     */
    suspend fun addPet(pet: Pet): Resource<Unit>

    /**
     * Updates the current user's profile data.
     */
    suspend fun updateUser(user: User): Resource<Unit>
}
