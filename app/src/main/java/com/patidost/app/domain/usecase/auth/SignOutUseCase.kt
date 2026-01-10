package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * SignOut UseCase - V10000.7800 Atomic Action.
 * RVWL: Session termination and memory cleanup.
 * V2: Synchronized with DomainResult standard.
 */
class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): DomainResult<Unit> {
        return authRepository.signOut()
    }
}
