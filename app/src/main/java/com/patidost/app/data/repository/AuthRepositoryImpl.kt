package com.patidost.app.data.repository

import com.patidost.app.data.models.AuthRequest
import com.patidost.app.data.models.OnboardingRequest
import com.patidost.app.data.remote.PatidostApi
import com.patidost.app.di.IoDispatcher
import com.patidost.app.domain.model.User
import com.patidost.app.domain.repository.AuthRepository
import com.patidost.app.domain.repository.SessionCache
import com.patidost.app.domain.util.DomainResult
import com.patidost.app.domain.util.toAppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val patidostApi: PatidostApi,
    private val sessionCache: SessionCache, // ÇELİK KASA enjekte edildi
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override fun getCurrentUser(): Flow<User?> = sessionCache.getAuthToken().map {
        if (it != null) {
            // TODO: Decode JWT to get user details for a real implementation
            User(id = "from_token", email = "from_token", name = "Patidost")
        } else {
            null
        }
    }

    override suspend fun signIn(email: String, password: String): DomainResult<User> = withContext(ioDispatcher) {
        try {
            val authRequest = AuthRequest(username = email, password = password)
            val response = patidostApi.signIn(authRequest)
            sessionCache.saveAuthToken(response.token) // ÇELİK KASA'ya yaz
            DomainResult.Success(User(id = "", email = email, name = "User"))
        } catch (e: Exception) {
            sessionCache.clearAuthToken() // Hata durumunda kasayı temizle
            DomainResult.Error(e.toAppError())
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): DomainResult<User> = withContext(ioDispatcher) {
        try {
            val authRequest = AuthRequest(username = email, password = password, name = name)
            patidostApi.signUp(authRequest)
            return@withContext signIn(email, password)
        } catch (e: Exception) {
            DomainResult.Error(e.toAppError())
        }
    }

    override suspend fun onboardUser(name: String, birthDate: String, hasPet: Boolean): DomainResult<Unit> = withContext(ioDispatcher) {
        try {
            val token = sessionCache.getAuthToken().first() // ÇELİK KASA'dan oku
                ?: return@withContext DomainResult.Error(com.patidost.app.domain.util.AppError.NotAuthorized())
            val request = OnboardingRequest(name = name, birthDate = birthDate, hasPet = hasPet)
            patidostApi.onboardUser(token, request)
            DomainResult.Success(Unit)
        } catch (e: Exception) {
            DomainResult.Error(e.toAppError())
        }
    }

    override suspend fun signOut(): DomainResult<Unit> = withContext(ioDispatcher) {
        sessionCache.clearAuthToken() // ÇELİK KASA'yı temizle
        DomainResult.Success(Unit)
    }

    override suspend fun deleteAccount(): DomainResult<Unit> = withContext(ioDispatcher) {
        DomainResult.Error(com.patidost.app.domain.util.AppError.UnknownError("Not implemented"))
    }

    override suspend fun signInWithGoogle(): DomainResult<User> = DomainResult.Error(com.patidost.app.domain.util.AppError.UnknownError("Not implemented"))
    override suspend fun signInWithFacebook(): DomainResult<User> = DomainResult.Error(com.patidost.app.domain.util.AppError.UnknownError("Not implemented"))
    override suspend fun signInWithInstagram(): DomainResult<User> = DomainResult.Error(com.patidost.app.domain.util.AppError.UnknownError("Not implemented"))
    override suspend fun getIntegrityToken(nonce: String): DomainResult<String> = DomainResult.Error(com.patidost.app.domain.util.AppError.UnknownError("Not implemented"))
}
