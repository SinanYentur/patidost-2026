package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * SignOut UseCase - V10000.7800 Atomic Action.
 * RVWL: Session termination and memory cleanup.
 */
class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.signOut()
    }
}
