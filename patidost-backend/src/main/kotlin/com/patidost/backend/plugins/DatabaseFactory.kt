package com.patidost.backend.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

/**
 * 🛡️ GÖREV-013 & 015: Veritabanı DNA'sını başlatan çekirdek.
 * Initializes the Exposed database connection using HikariCP for connection pooling.
 */
fun Application.configureDatabase() {
    val config = environment.config.config("ktor.database")
    val dbDriver = config.property("driver").getString()
    val dbUrl = config.property("url").getString()
    val dbUser = config.property("user").getString()
    val dbPassword = config.property("password").getString()

    val hikariConfig = HikariConfig().apply {
        driverClassName = dbDriver
        jdbcUrl = dbUrl
        username = dbUser
        password = dbPassword
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    }

    val dataSource = HikariDataSource(hikariConfig)
    Database.connect(dataSource)

    log.info("Database connection initialized.")
}
