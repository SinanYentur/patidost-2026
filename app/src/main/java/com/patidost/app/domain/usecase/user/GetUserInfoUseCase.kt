package com.patidost.app.domain.usecase.user

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.UserRepository
import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * UseCase to stream user information by ID.
 * Fixed: Synchronized suspend repository call with Flow return type.
 */
class GetUserInfoUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String): Flow<User> = flow {
        val result = repository.getUserById(userId)
        if (result is DomainResult.Success) {
            emit(result.data)
        } else {
            emit(User.EMPTY)
        }
    }
}
