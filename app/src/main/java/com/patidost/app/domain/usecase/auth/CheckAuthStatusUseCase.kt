package com.patidost.app.domain.usecase.auth

import com.patidost.app.core.util.Resource
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import javax.inject.Inject

class CheckAuthStatusUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Resource<User> {
        return authRepository.getCurrentUser()
    }
}
