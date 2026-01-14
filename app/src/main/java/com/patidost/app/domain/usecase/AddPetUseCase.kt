package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.repository.PetRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

class AddPetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    suspend operator fun invoke(pet: Pet): Resource<Unit> {
        if (pet.name.isBlank()) {
            return Resource.Error(UiText.DynamicString("Pet name can't be empty."))
        }
        if (pet.breed.isBlank()) {
            return Resource.Error(UiText.DynamicString("Pet breed can't be empty."))
        }
        // Add more validation as needed
        return petRepository.addPet(pet)
    }
}
