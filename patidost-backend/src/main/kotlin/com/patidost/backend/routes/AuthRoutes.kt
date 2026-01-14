package com.patidost.backend.routes

import com.patidost.backend.dto.AuthRequest
import com.patidost.backend.services.AuthRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.authRoutes(repository: AuthRepository) {

    route("/auth") {
        post("/register") {
            val authRequest = call.receive<AuthRequest>()
            repository.register(authRequest)
            call.respond(HttpStatusCode.Created, "User registered successfully")
        }

        post("/login") {
            val authRequest = call.receive<AuthRequest>()
            val authResponse = repository.login(authRequest, call.application.environment.config)
            call.respond(authResponse)
        }
    }
}
