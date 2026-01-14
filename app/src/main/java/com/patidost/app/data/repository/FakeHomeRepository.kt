package com.patidost.app.data.repository

import com.patidost.app.domain.repository.HomeRepository
import com.patidost.app.presentation.ui.screen.home.FeaturedPet
import com.patidost.app.presentation.ui.screen.home.PetOwner
import com.patidost.app.presentation.ui.screen.home.TopGiver
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeHomeRepository @Inject constructor() : HomeRepository {

    override suspend fun getTopGivers(): Result<List<TopGiver>> {
        delay(1000) // Simulate network delay
        val fakeData = listOf(
            TopGiver("Melis", "", 14),
            TopGiver("Arda", "", 10),
            TopGiver("Emir", "", 7),
            TopGiver("Tuğba", "", 6),
            TopGiver("Elif", "", 5)
        )
        return Result.success(fakeData)
    }

    override suspend fun getFeaturedPet(): Result<FeaturedPet?> {
        delay(1200) // Simulate network delay
        val fakeOwner = PetOwner("SibelR.", "")
        val fakePet = FeaturedPet(
            name = "Süslü",
            breed = "Golden Retriever",
            imageUrl = "",
            patiId = "40351824",
            patiScore = 328,
            owner = fakeOwner,
            age = 2,
            gender = "Erkek",
            birthDate = "11 Nisan 2022"
        )
        return Result.success(fakePet)
    }
}
