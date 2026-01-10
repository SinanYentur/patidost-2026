package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * DeleteUser UseCase - V3000.70 Atomic Action.
 * RVWL: GDPR Article 17 Compliance - Permanent Account Deletion.
 * V2: Synchronized with DomainResult standard.
 */
class DeleteUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): DomainResult<Unit> {
        return authRepository.deleteAccount()
    }
}
