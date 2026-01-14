package com.patidost.app.data.remote

import com.patidost.app.domain.model.Pet
import com.patidost.app.presentation.ui.screen.home.TopGiver

/**
 * A mock ApiService to be replaced with a real implementation (e.g., Retrofit).
 */
interface ApiService {

    suspend fun getFeaturedPets(): List<Pet> // In a real app, this would be List<PetDto>

    suspend fun getTopGivers(): List<TopGiver>
}
