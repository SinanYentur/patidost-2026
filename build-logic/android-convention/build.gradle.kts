plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:8.3.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
}

gradlePlugin {
    plugins {
        register("android-convention") {
            id = "patidost.android.convention"
            implementationClass = "AndroidConventionPlugin"
        }
    }
}
