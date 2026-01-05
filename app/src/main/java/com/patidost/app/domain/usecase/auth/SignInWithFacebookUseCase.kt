package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * SignInWithFacebook UseCase - V10000.8300 Social Identity.
 * RVWL: Formal execution of Facebook Federated Authentication.
 */
class SignInWithFacebookUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<User> {
        return authRepository.signInWithFacebook()
    }
}
