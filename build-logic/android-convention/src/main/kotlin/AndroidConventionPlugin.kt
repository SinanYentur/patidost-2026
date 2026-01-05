import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.withId("com.android.application") {
            project.extensions.configure<AppExtension> {
                configureAndroidBase(this)
            }
        }

        project.plugins.withId("com.android.library") {
            project.extensions.configure<LibraryExtension> {
                configureAndroidBase(this)
            }
        }

        project.plugins.withId("com.android.test") {
            project.extensions.configure<TestExtension> {
                configureAndroidBase(this)
            }
        }
        
        project.tasks.withType(KotlinCompile::class.java).configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }
    }

    private fun configureAndroidBase(extension: BaseExtension) {
        extension.compileSdkVersion(36)
        extension.defaultConfig {
            minSdk = 24
            targetSdk = 36
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        extension.compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}
