package com.patidost.app.data.remote

import com.patidost.app.data.remote.dto.PetDto
import com.patidost.app.presentation.ui.screen.home.TopGiver
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * A fake implementation of the ApiService for development and testing purposes.
 * It simulates network latency with a delay.
 */
class FakeApiService @Inject constructor() : ApiService {

    override suspend fun getFeaturedPets(): List<PetDto> {
        delay(1500) // Simulate network latency
        return listOf(
            PetDto(
                pet_id = "1",
                pet_name = "Mırmır",
                pet_breed = "Tekir",
                primary_image_url = "https://patidost.dev/mirmir.jpg",
                pet_age = 3,
                owner_name = "Ayşe"
            ),
            PetDto(
                pet_id = "2",
                pet_name = "Paşa",
                pet_breed = "Sivas Kangalı",
                primary_image_url = "https://patidost.dev/pasa.jpg",
                pet_age = 5,
                owner_name = "Ahmet"
            )
        )
    }

    override suspend fun getTopGivers(): List<TopGiver> {
        delay(1000)
        return emptyList()
    }
}
