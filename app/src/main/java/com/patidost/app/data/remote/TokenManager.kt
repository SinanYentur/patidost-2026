package com.patidost.app.data.remote

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
