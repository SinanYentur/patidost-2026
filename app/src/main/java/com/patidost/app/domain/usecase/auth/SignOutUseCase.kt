package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.repository.UserRepository
import javax.inject.Inject

/**
 * UseCase to sign out the current user.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
class SignOutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return userRepository.signOut()
    }
}
