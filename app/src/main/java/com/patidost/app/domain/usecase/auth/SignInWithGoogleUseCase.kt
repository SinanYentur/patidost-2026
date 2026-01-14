package com.patidost.app.domain.usecase.auth

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.presentation.ui.util.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(idToken: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val result = authRepository.signInWithGoogle(idToken)
            emit(result)
        } catch (e: Exception) {
            emit(Resource.Error(UiText.DynamicString(e.localizedMessage ?: "An unexpected error occurred")))
        }
    }
}
