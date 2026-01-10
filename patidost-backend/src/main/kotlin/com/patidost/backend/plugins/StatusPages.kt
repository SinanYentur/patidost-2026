package com.patidost.backend.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import java.sql.SQLException

/**
 * 🛡️ GÖREV-029: Hata Yönetim Zırhı
 * V2: Adapted for H2 database by catching generic SQLException, removing PostgreSQL-specific code.
 */
fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<RequestValidationException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
        }

        exception<SQLException> { call, cause ->
            // SQLState 23505 is for unique_violation in H2 and PostgreSQL
            if (cause.sqlState == "23505") {
                call.respond(HttpStatusCode.Conflict, "A user with this username already exists.")
            } else {
                // For other database errors, respond with a generic server error
                call.respond(HttpStatusCode.InternalServerError, "Database error occurred.")
            }
        }

        // Generic exception handler
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, "An unexpected error occurred: ${cause.localizedMessage}")
        }
    }
}
