package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.repository.UserRepository
import javax.inject.Inject

/**
 * UseCase to delete the current user account.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return userRepository.deleteAccount()
    }
}
