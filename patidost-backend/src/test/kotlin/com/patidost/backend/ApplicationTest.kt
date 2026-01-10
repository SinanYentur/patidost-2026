package com.patidost.backend

import com.patidost.backend.dto.AuthRequest
import com.patidost.backend.dto.AuthResponse
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.*
import io.ktor.server.testing.*
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ApplicationTest {

    @Test
    fun `test register and login`() = testApplication {
        environment {
            // 🛡️ GÖREV-038: Test ortamı H2 in-memory veritabanı ile izole edildi.
            config = MapApplicationConfig(
                "ktor.database.driver" to "org.h2.Driver",
                "ktor.database.url" to "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                "ktor.database.user" to "sa",
                "ktor.database.password" to "",
                "ktor.jwt.secret" to "test-secret",
                "ktor.jwt.issuer" to "https://patidost.com",
                "ktor.jwt.audience" to "patidost-mobile-app",
                "ktor.jwt.realm" to "Patidost Backend Test"
            )
        }

        application {
            module()
        }

        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // 1. Register a new user
        val username = "testuser_${UUID.randomUUID()}"
        val registerRequest = AuthRequest(username, "password123")
        val registerResponse = client.post("/register") {
            contentType(ContentType.Application.Json)
            setBody(registerRequest)
        }
        assertEquals(HttpStatusCode.Created, registerResponse.status)
        assertEquals("User registered successfully", registerResponse.body<String>())

        // 2. Login with the new user
        val loginRequest = AuthRequest(username, "password123")
        val loginResponse = client.post("/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }
        assertEquals(HttpStatusCode.OK, loginResponse.status)
        val authResponse = loginResponse.body<AuthResponse>()
        assertNotNull(authResponse.token, "Token should not be null")
    }
}
