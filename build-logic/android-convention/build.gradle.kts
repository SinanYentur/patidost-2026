plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.13.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
}

gradlePlugin {
    plugins {
        register("android-convention") {
            id = "patidost.android.convention"
            implementationClass = "AndroidConventionPlugin"
        }
    }
}
