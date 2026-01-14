package com.patidost.backend

import com.patidost.backend.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // All plugins are configured here.
    // SeedingService call removed for a clean slate.
    configureDatabase()
    configureRouting()
    configureSecurity()
    configureSerialization()
}
