package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.presentation.ui.screen.home.TopGiver
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

data class HomeFeed(val topGivers: List<TopGiver>, val featuredPet: Pet?)

class GetHomeFeedUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<HomeFeed> {
        return coroutineScope {
            val topGiversDeferred = async { repository.getTopGivers() }
            val featuredPetDeferred = async { repository.getFeaturedPet() }

            val topGiversResult = topGiversDeferred.await()
            val featuredPetResult = featuredPetDeferred.await()

            val firstError = (topGiversResult as? Resource.Error)?.message
                ?: (featuredPetResult as? Resource.Error)?.message

            if (firstError != null) {
                Resource.Error(firstError)
            } else {
                Resource.Success(
                    HomeFeed(
                        topGivers = (topGiversResult as Resource.Success).data ?: emptyList(),
                        featuredPet = (featuredPetResult as Resource.Success).data
                    )
                )
            }
        }
    }
}
