package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import javax.inject.Inject

/**
 * UseCase to sign in a user.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, pass: String): Result<User> {
        return userRepository.signIn(email, pass)
    }
}
