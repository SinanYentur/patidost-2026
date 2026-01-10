val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project // PostgreSQL yerine H2
val hikaricp_version: String by project
val bcrypt_version: String by project
val coroutines_version: String by project

plugins {
    kotlin("jvm")
    id("io.ktor.plugin") version "2.3.10"
    kotlin("plugin.serialization")
}

group = "com.patidost.backend"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

// 🛡️ GÖREV-036: Bağımlılık versiyonlarını zorla eşitle
configurations.all {
    resolutionStrategy {
        force("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
        force("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$coroutines_version")
        force("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    }
}

dependencies {
    // Ktor Core
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")

    // 🛡️ Güvenlik, Doğrulama ve Hata Yönetimi DNA'sı
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-request-validation:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version") 
    implementation("at.favre.lib:bcrypt:$bcrypt_version")

    // Content Negotiation & Serialization
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")

    // 🛡️ Veritabanı DNA'sı (YEREL BESİN KAYNAĞI)
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version") // PostgreSQL yerine H2
    implementation("com.zaxxer:HikariCP:$hikaricp_version")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Testing
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    testImplementation("io.ktor:ktor-client-serialization:$ktor_version")
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation("com.h2database:h2:$h2_version") 
}
