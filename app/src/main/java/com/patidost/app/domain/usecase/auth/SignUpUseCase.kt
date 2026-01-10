package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.model.valueobject.EmailVO
import com.patidost.app.domain.model.valueobject.PasswordVO
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * SignUp UseCase - V10000.7700 Atomic Action.
 * RVWL: Formal execution of user registration.
 * V3: Enforced Type-Safe Value Objects for parameters.
 */
class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: EmailVO, pass: PasswordVO, name: String): DomainResult<User> {
        return authRepository.signUp(email.value, pass.value, name)
    }
}
