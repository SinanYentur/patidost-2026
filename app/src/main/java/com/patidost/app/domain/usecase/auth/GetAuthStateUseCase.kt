package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * GetAuthState UseCase - V10000.7900 Atomic Action.
 * RVWL: Reactive session monitoring via SSOT.
 */
class GetAuthStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<User?> {
        return authRepository.getCurrentUser()
    }
}
