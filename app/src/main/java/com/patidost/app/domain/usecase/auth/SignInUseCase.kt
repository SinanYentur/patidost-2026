package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import com.patidost.app.domain.model.valueobject.EmailVO
import com.patidost.app.domain.model.valueobject.PasswordVO
import com.patidost.app.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 🛡️ Rule 300: Domain Security Hardening.
 * Replaced String parameters with atomic Value Objects.
 * V2: Corrected repository dependency to AuthRepository.
 */
class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(email: EmailVO, pass: PasswordVO): DomainResult<com.patidost.app.domain.model.User> =
        withContext(ioDispatcher) {
            // Validation is now enforced at the Type level (EmailVO/PasswordVO)
            repository.signIn(email.value, pass.value)
        }
}
