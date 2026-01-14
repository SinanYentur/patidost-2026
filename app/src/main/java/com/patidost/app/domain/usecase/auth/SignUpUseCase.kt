package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 🛡️ GÖREV 0: SIFIR NOKTASI
 * Kullanıcı kaydı için tekil iş mantığını kapsar.
 * Sadece AuthRepository arayüzüne bağımlıdır.
 */
class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(email: String, password: String, name: String): DomainResult<User> {
        return withContext(dispatcher) {
            authRepository.signUp(email, password, name)
        }
    }
}
