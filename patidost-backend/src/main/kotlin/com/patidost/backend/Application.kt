package com.patidost.backend

import com.patidost.backend.plugins.*
import com.patidost.backend.services.SeedingService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureStatusPages()   // 🛡️ GÖREV-029: Hata Yönetim Zırhı aktif.
    configureDatabase()      // 🛡️ Veritabanı bağlantısı kuruluyor.
    SeedingService.seed()    // 🛡️ Veritabanı tabloları oluşturuluyor ve tohumlanıyor.
    configureSerialization() // 🛡️ JSON (içerik) anlaşması yapılandırılıyor.
    configureSecurity()      // 🛡️ Güvenlik (JWT) DNA'sı aktif.
    configureValidation()    // 🛡️ Girdi Kontrol Zırhı aktif.
    configureRouting()       // 🛡️ API rotaları etkinleştiriliyor.
}
