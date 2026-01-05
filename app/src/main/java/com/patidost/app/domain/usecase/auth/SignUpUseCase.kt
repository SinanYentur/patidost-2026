package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * SignUp UseCase - V10000.7700 Atomic Action.
 * RVWL: Formal execution of user registration.
 */
class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, pass: String, name: String): Result<User> {
        return authRepository.signUp(email, pass, name)
    }
}
