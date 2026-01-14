package com.patidost.app.data.remote.config

/**
 * Defines the different build environments for the application.
 * Each environment has its own specific configurations like base URL.
 */
sealed class Environment(val name: String, val baseUrl: String) {
    object Dev : Environment(name = "dev", baseUrl = "https://dev.patidost.com/api/")
    object Staging : Environment(name = "staging", baseUrl = "https://staging.patidost.com/api/")
    object Prod : Environment(name = "prod", baseUrl = "https://api.patidost.com/")
}
