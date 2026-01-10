package com.patidost.app.data.cache

import android.content.SharedPreferences
import com.patidost.app.domain.repository.SessionCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SessionCacheImpl @Inject constructor(
    private val prefs: SharedPreferences
) : SessionCache {

    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    override fun getAuthToken(): Flow<String?> = flow {
        emit(prefs.getString(KEY_AUTH_TOKEN, null))
    }

    override suspend fun saveAuthToken(token: String) {
        prefs.edit().putString(KEY_AUTH_TOKEN, token).apply()
    }

    override suspend fun clearAuthToken() {
        prefs.edit().remove(KEY_AUTH_TOKEN).apply()
    }
}
