package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.Pet
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<Pair<User, List<Pet>>>> {
        val userFlow = userRepository.getUserProfile(userId)
        val petsFlow = userRepository.getPetsForUser(userId)

        return combine(userFlow, petsFlow) { userResult, petsResult ->
            when {
                userResult is Resource.Success && petsResult is Resource.Success -> {
                    Resource.Success(Pair(userResult.data!!, petsResult.data!!))
                }
                userResult is Resource.Error -> {
                    Resource.Error(userResult.message!!)
                }
                petsResult is Resource.Error -> {
                    Resource.Error(petsResult.message!!)
                }
                else -> {
                    Resource.Loading()
                }
            }
        }
    }
}
