package com.patidost.app.data.remote

import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.presentation.ui.screen.home.TopGiver
import kotlinx.coroutines.delay
import java.util.Date
import javax.inject.Inject

class FakeApiService @Inject constructor() : ApiService {

    override suspend fun getFeaturedPets(): List<PetDto> {
        delay(1500)
        return listOf(
            PetDto(
                name = "Mırmır",
                breed = "Tekir",
                photoUrl = "https://patidost.dev/mirmir.jpg",
                ownerId = "user_ayse_123",
                birthDate = Date() // Use birthDate instead of age
            ).apply {
                id = "1" // Manually assign the ID as per new DTO design
            }
        )
    }

    override suspend fun getTopGivers(): List<TopGiver> {
        delay(1000)
        return emptyList()
    }

    override suspend fun likePost(postId: String) {
        delay(500)
        println("Fake API: Liked post $postId")
    }

    override suspend fun unlikePost(postId: String) {
        delay(500)
        println("Fake API: Unliked post $postId")
    }

    override suspend fun givePatiPoints(donationRequest: DonationRequest) {
        delay(1000)
        println("Fake API: Received donation: $donationRequest")
    }
}
