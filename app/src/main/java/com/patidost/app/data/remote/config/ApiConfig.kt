package com.patidost.app.data.remote.config

import com.patidost.app.BuildConfig

/**
 * Provides the active API configuration based on the current build type.
 * This is the single source of truth for environment-specific values.
 * It enforces the "Environment Contract" law.
 */
object ApiConfig {

    val CURRENT_ENVIRONMENT: Environment = when (BuildConfig.BUILD_TYPE) {
        "release" -> Environment.Prod
        "staging" -> Environment.Staging
        else -> Environment.Dev // Default to "debug" or any other custom build types
    }

    val BASE_URL: String
        get() = CURRENT_ENVIRONMENT.baseUrl

    val IS_LOGGING_ENABLED: Boolean
        get() = CURRENT_ENVIRONMENT !is Environment.Prod || BuildConfig.DEBUG
}
