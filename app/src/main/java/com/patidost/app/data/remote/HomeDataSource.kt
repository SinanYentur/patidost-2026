package com.patidost.app.data.remote

import com.patidost.app.core.util.Resource
import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.domain.model.TopGiver
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {
    fun getFeaturedPets(): Flow<Resource<List<PetDto>>>
    fun getTopGivers(): Flow<Resource<List<TopGiver>>>
}
