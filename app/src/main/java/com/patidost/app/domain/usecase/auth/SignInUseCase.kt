package com.patidost.app.domain.usecase.auth

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * 🛡️ GÖREV 0: SIFIR NOKTASI
 * Kullanıcı girişi için tekil iş mantığını kapsar.
 * Sadece AuthRepository arayüzüne bağımlıdır.
 */
class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher // Hilt ile sağlanacak (örn: Dispatchers.IO)
) {
    suspend operator fun invoke(email: String, password: String): DomainResult<User> {
        return withContext(dispatcher) {
            authRepository.signIn(email, password)
        }
    }
}
