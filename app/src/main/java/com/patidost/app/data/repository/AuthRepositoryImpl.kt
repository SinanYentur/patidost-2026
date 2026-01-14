package com.patidost.app.data.repository

import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.util.DomainResult
import javax.inject.Inject

/**
 * 🛡️ GÖREV 0: SIFIR NOKTASI
 * AuthRepository kontratının somut implementasyonu.
 * Bu sınıf, kimlik doğrulama verilerini nereden alacağına (remote, local) karar verir.
 */
class AuthRepositoryImpl @Inject constructor(
    // private val authApi: AuthApi, // Remote data source
    // private val userDao: UserDao // Local data source
) : AuthRepository {

    override suspend fun signIn(email: String, password: String): DomainResult<User> {
        // Implementasyon buraya gelecek
        return DomainResult.Error(NotImplementedError("SignIn not implemented"))
    }

    override suspend fun signUp(email: String, password: String, name: String): DomainResult<User> {
        // Implementasyon buraya gelecek
        return DomainResult.Error(NotImplementedError("SignUp not implemented"))
    }

    override fun getCurrentUser(): User? {
        // Implementasyon buraya gelecek
        return null
    }
}
