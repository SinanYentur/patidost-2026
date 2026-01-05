package com.patidost.app.data.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.patidost.app.UserPreferences
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * 🛡️ Rule 300: Persistence Hardening.
 * Includes CorruptionHandler to prevent app crashes on faulty preference files.
 */
class UserPrefsSerializer @Inject constructor() : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        return try {
            UserPreferences.parseFrom(input)
        } catch (e: Exception) {
            // 🛡️ Mühür: Eksik 2 - Corruption Recovery
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}
