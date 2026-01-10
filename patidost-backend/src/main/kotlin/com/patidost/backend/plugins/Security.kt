package com.patidost.backend.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

/**
 * 🛡️ GÖREV-018: Güvenlik DNA'sını başlatan çekirdek.
 * Configures JWT Authentication.
 */
fun Application.configureSecurity() {
    val config = environment.config.config("ktor.jwt")
    val secret = config.property("secret").getString()
    val issuer = config.property("issuer").getString()
    val audience = config.property("audience").getString()
    val realm = config.property("realm").getString()

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JWT
                .require(Algorithm.HMAC256(secret))
                .withAudience(audience)
                .withIssuer(issuer)
                .build())
            validate { credential ->
                if (credential.payload.getClaim("username").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
}
