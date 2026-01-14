plugins {
    kotlin("jvm")
    id("io.ktor.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "com.patidost.backend"
version = "1.0.0"

application {
    mainClass.set("com.patidost.backend.ApplicationKt")
}

// Repositories and plugin versions are managed centrally in settings.gradle.kts

dependencies {
    // Ktor Core
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.14")

    // Serialization
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")

    // Security & JWT
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")

    // Database (Exposed)
    implementation("org.jetbrains.exposed:exposed-core:0.45.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.1")

    // Validation
    implementation("io.ktor:ktor-server-request-validation:2.3.7")
    
    // Status Pages for exception handling
    implementation("io.ktor:ktor-server-status-pages:2.3.7")

    // --- TEST DEPENDENCIES ---
    // H2 Database for in-memory testing
    testImplementation("com.h2database:h2:2.2.224")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation(kotlin("test-junit"))
}
