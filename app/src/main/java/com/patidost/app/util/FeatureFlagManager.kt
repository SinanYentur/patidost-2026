package com.patidost.app.util

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 🛡️ FeatureFlagManager - V10000.70023 Sovereign Config.
 * Rule 420: Fixed KTX resolution and modernized Firebase implementation.
 */
@Singleton
class FeatureFlagManager @Inject constructor() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance().apply {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        setConfigSettingsAsync(configSettings)
    }

    fun isEnabled(flag: String): Boolean = remoteConfig.getBoolean(flag)
    fun getString(flag: String): String = remoteConfig.getString(flag)
    
    object Flags {
        const val NEW_PET_CARD_LAYOUT = "new_pet_card_layout"
        const val ADOPTION_FEE_ENABLED = "adoption_fee_enabled"
        const val MULTI_LANGUAGE_BETA = "multi_language_beta"
    }
}
