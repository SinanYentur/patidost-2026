package com.patidost.backend.routes

import com.patidost.backend.services.SocialRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// A simple DTO for this specific request, kept private to this route.
private data class PatiPuanRequest(val receiverPetId: String)

fun Route.socialRoutes(repository: SocialRepository) {
    authenticate("auth-jwt") {
        route("/social") {
            /**
             * Gives a "Pati Puan" to a pet.
             * This is a transactional operation governed by the SocialRepository.
             */
            post("/pati-puan") {
                val principal = call.principal<JWTPrincipal>()
                    ?: return@post call.respond(HttpStatusCode.Unauthorized)

                val giverId = principal.payload.getClaim("userId").asString()
                val request = call.receive<PatiPuanRequest>()

                repository.givePatiPuan(giverId, request.receiverPetId)

                // 201 Created is more appropriate as a new transaction resource is created.
                call.respond(HttpStatusCode.Created, "Pati Puan transaction recorded successfully.")
            }
        }
    }
}
