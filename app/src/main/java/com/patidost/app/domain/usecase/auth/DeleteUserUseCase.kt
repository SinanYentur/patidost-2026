package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * DeleteUser UseCase - V3000.70 Atomic Action.
 * RVWL: GDPR Article 17 Compliance - Permanent Account Deletion.
 */
class DeleteUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.deleteAccount()
    }
}
