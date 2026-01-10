package com.patidost.backend.plugins

import com.patidost.backend.dto.AuthRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

/**
 * 🛡️ GÖREV-027: Girdi Kontrol Zırhı
 * Configures input validation for incoming DTOs.
 */
fun Application.configureValidation() {
    install(RequestValidation) {
        validate<AuthRequest> {
            if (it.username.isBlank()) {
                ValidationResult.Invalid("Username cannot be blank.")
            } else if (it.password.length < 8) {
                ValidationResult.Invalid("Password must be at least 8 characters long.")
            } else {
                ValidationResult.Valid
            }
        }
    }
}
