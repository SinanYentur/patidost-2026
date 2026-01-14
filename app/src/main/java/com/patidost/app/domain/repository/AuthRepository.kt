package com.patidost.app.domain.repository

import com.patidost.app.domain.model.User
import com.patidost.app.domain.util.DomainResult

/**
 * 🛡️ GÖREV 0: SIFIR NOKTASI
 * Veri katmanının sağlaması gereken kimlik doğrulama işlemlerinin kontratı.
 * UseCase'ler bu arayüzle konuşur, implementasyon detayını bilmez.
 */
interface AuthRepository {

    suspend fun signIn(email: String, password: String): DomainResult<User>

    suspend fun signUp(email: String, password: String, name: String): DomainResult<User>

    fun getCurrentUser(): User?

}
