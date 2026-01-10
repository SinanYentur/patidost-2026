package com.patidost.backend.dto

import kotlinx.serialization.Serializable

/**
 * 🛡️ GÖREV-018: Güvenlik DNA'sı
 * Data class for the response after a successful login.
 */
@Serializable
data class AuthResponse(
    val token: String
)
