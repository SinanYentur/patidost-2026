package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * SignInWithGoogle UseCase - V10000.8200 Social Identity.
 * RVWL: Formal execution of Google Federated Authentication.
 * V2: Synchronized with DomainResult standard.
 */
class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): DomainResult<User> {
        return authRepository.signInWithGoogle()
    }
}
