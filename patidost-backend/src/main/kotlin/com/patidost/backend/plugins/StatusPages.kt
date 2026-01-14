package com.patidost.backend.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import java.sql.SQLException

/**
 * 🛡️ GÖREV-029: Hata Yönetim Zırhı
 * V4: Anatomik Onarım - 'application.log' referansı 'call.application.log' olarak düzeltildi.
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
                // For other database errors, log the details and respond with a generic server error.
                call.application.log.error("Unhandled SQL exception caught in StatusPages", cause)
                call.respond(HttpStatusCode.InternalServerError, "A database error occurred.")
            }
        }

        // Generic exception handler - Hardened for security
        exception<Throwable> { call, cause ->
            // Log the full error for server-side debugging.
            call.application.log.error("Unhandled generic exception caught in StatusPages", cause)
            // NEVER send exception details to the client in production.
            call.respond(HttpStatusCode.InternalServerError, "An unexpected internal server error occurred.")
        }
    }
}
