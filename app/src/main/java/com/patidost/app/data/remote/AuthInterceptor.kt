package com.patidost.app.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A Singleton class to manage the authentication token.
 * In a real app, this would use EncryptedSharedPreferences.
 */
@Singleton
class TokenManager @Inject constructor() {
    private var token: String? = null

    fun getToken(): String? {
        // For now, return a dummy token. Later, this will read from a secure storage.
        return token ?: "DUMMY_BEARER_TOKEN"
    }

    fun saveToken(token: String) {
        this.token = token
    }
}

/**
 * Intercepts network requests to add the Authorization header.
 */
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}
