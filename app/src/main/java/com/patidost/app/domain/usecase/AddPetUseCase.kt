package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

/**
 * Use case for adding a new pet.
 * It also triggers the creation of a "Welcome" post.
 */
class AddPetUseCase @Inject constructor(
    private val userRepository: UserRepository // Assuming this repo handles pet creation
) {
    suspend operator fun invoke(name: String, breed: String, age: Int, imageUrl: String): Resource<Unit> {
        if (name.isBlank() || breed.isBlank()) {
            return Resource.Error(UiText.DynamicString("İsim ve tür alanları boş bırakılamaz."))
        }
        if (age <= 0) {
            return Resource.Error(UiText.DynamicString("Yaş geçerli bir sayı olmalıdır."))
        }
        
        // The repository will handle the atomic operation of adding a pet and creating a post.
        return userRepository.addPet(name, breed, age, imageUrl)
    }
}
