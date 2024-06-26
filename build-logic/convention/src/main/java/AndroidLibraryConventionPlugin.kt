import com.android.build.gradle.LibraryExtension
import com.beeeam.convention.configureKotlinAndroid
import com.beeeam.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("UNUSED")
class AndroidLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
      }

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid(this)

        viewBinding.enable = true
      }

      dependencies {
        "testImplementation"(libs.findLibrary("junit").get())
        "androidTestImplementation"(libs.findLibrary("androidx.ext.junit").get())
        "androidTestImplementation"(libs.findLibrary("androidx.espresso.core").get())
      }
    }
  }
}
