package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UseCase to observe authentication state.
 * RVWL: Synchronized with com.patidost.app package identity.
 */
class GetAuthStateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<User?> {
        return userRepository.getCurrentUser()
    }
}
