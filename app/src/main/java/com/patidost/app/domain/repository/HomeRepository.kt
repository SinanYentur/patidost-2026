package com.patidost.app.domain.repository

import com.patidost.app.presentation.ui.screen.home.FeaturedPet
import com.patidost.app.presentation.ui.screen.home.TopGiver

/**
 * Interface for the repository that handles home screen data.
 */
interface HomeRepository {

    /**
     * Fetches the list of top givers.
     */
    suspend fun getTopGivers(): Result<List<TopGiver>>

    /**
     * Fetches the featured pet.
     */
    suspend fun getFeaturedPet(): Result<FeaturedPet?>
}
