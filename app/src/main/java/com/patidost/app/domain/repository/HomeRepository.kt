package com.patidost.app.domain.repository

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.TopGiver
import kotlinx.coroutines.flow.Flow

/**
 * The constitutional contract for the Home screen's primary data operations.
 */
interface HomeRepository {
    /**
     * Fetches a list of featured pets for the home screen.
     */
    fun getFeaturedPets(): Flow<Resource<List<Pet>>>

    /**
     * Fetches the list of top givers for the weekly leaderboard.
     */
    fun getTopGivers(): Flow<Resource<List<TopGiver>>>
}
