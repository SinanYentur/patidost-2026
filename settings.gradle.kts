pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    // Central plugin version definition
    plugins {
        id("com.android.application") version "8.2.2" apply false
        id("com.android.library") version "8.2.2" apply false
        id("org.jetbrains.kotlin.android") version "1.9.21" apply false
        id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
        id("com.google.dagger.hilt.android") version "2.50" apply false
        // Ktor plugin for backend
        id("io.ktor.plugin") version "2.3.7" apply false
        // Kotlin JVM and Serialization for backend
        kotlin("jvm") version "1.9.21" apply false
        id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21" apply false
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}

rootProject.name = "patidost"
include(":app")
include(":baselineprofile")
include(":patidost-backend")
