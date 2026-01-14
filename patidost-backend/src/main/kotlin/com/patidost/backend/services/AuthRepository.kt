package com.patidost.backend.services

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.patidost.backend.dto.AuthRequest
import com.patidost.backend.dto.AuthResponse
import com.patidost.backend.models.Users
import com.patidost.backend.plugins.AuthenticationException
import com.patidost.backend.plugins.dbQuery
import io.ktor.server.config.ApplicationConfig
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.util.*

interface AuthRepository {
    suspend fun register(authRequest: AuthRequest): String
    suspend fun login(authRequest: AuthRequest, config: ApplicationConfig): AuthResponse
}

class AuthRepositoryImpl : AuthRepository {
    override suspend fun register(authRequest: AuthRequest): String {
        val passwordHash = BCrypt.withDefaults().hashToString(12, authRequest.password.toCharArray())
        val userId = UUID.randomUUID().toString()

        dbQuery {
            Users.insert {
                it[id] = userId
                it[username] = authRequest.username
                it[name] = authRequest.name ?: authRequest.username
                it[Users.passwordHash] = passwordHash
                it[profileImageUrl] = "/images/default_avatar.jpg"
            }
        }
        return userId
    }

    override suspend fun login(authRequest: AuthRequest, config: ApplicationConfig): AuthResponse {
        val user = dbQuery {
            Users.selectAll().where { Users.username eq authRequest.username }.singleOrNull()
        } ?: throw AuthenticationException()

        val result = BCrypt.verifyer().verify(authRequest.password.toCharArray(), user[Users.passwordHash])

        if (!result.verified) {
            throw AuthenticationException()
        }

        val secret = config.property("ktor.jwt.secret").getString()
        val issuer = config.property("ktor.jwt.issuer").getString()
        val audience = config.property("ktor.jwt.audience").getString()

        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", user[Users.username])
            .withClaim("userId", user[Users.id])
            .withExpiresAt(Date(System.currentTimeMillis() + 60_000_000)) // 1000 minutes
            .sign(Algorithm.HMAC256(secret))

        return AuthResponse(token)
    }
}
