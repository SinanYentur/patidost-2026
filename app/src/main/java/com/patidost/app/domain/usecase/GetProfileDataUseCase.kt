package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        when (val result = userRepository.getUser(userId, forceRefresh = false)) {
            is Resource.Success -> {
                if (result.data != null) {
                    emit(Resource.Success(result.data))
                } else {
                    emit(Resource.Error(UiText.DynamicString("User not found")))
                }
            }
            is Resource.Error -> {
                emit(Resource.Error(result.message ?: UiText.DynamicString("An unknown error occurred.")))
            }
            else -> {}
        }
    }
}
