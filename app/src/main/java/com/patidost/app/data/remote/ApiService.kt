package com.patidost.app.data.remote

import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.presentation.ui.screen.home.TopGiver
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Defines the abstract methods for making network requests using Retrofit.
 */
interface ApiService {

    suspend fun getFeaturedPets(): List<PetDto>
    suspend fun getTopGivers(): List<TopGiver>

    // Interaction Engine
    suspend fun likePost(postId: String)
    suspend fun unlikePost(postId: String)

    // Donation Engine
    @POST("donation/give")
    suspend fun givePatiPoints(@Body donationRequest: DonationRequest) // Assuming a request body
}

// A new DTO for the donation request body
data class DonationRequest(
    val fromUserId: String,
    val toPetId: String,
    val amount: Int
)
