package com.patidost.backend.dto

import kotlinx.serialization.Serializable

/**
 * 🛡️ GÖREV-018: Güvenlik DNA'sı
 * Data class for login and register requests.
 * V2: Added nullable name for registration.
 */
@Serializable
data class AuthRequest(
    val username: String,
    val password: String,
    val name: String? = null
)
