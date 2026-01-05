package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * SignInWithGoogle UseCase - V10000.8200 Social Identity.
 * RVWL: Formal execution of Google Federated Authentication.
 */
class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<User> {
        return authRepository.signInWithGoogle()
    }
}
