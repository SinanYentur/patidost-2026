package com.patidost.app.domain.usecase.user

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * UseCase to stream user information by ID.
 * PD-LOCKS Compliance: Nullability Escape Lock & Flow Lock.
 */
class GetUserInfoUseCase @Inject constructor(
    private val repository: UserRepository
) {
    /**
     * Executes the observation flow.
     * Returns User.EMPTY on error or if user is not found.
     */
    operator fun invoke(userId: String): Flow<User> {
        return repository.getUserById(userId)
            .map { it ?: User.EMPTY }
            .catch { emit(User.EMPTY) }
    }
}
