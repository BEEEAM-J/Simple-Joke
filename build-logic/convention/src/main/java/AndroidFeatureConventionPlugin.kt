import com.beeeam.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal class AndroidFeatureConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("simplejoke.android.library")
        apply("simplejoke.android.library.compose")
        apply("simplejoke.android.hilt")
      }

      dependencies {
        "implementation"(project(":domain"))

        "implementation"(libs.findLibrary("androidx.appcompat").get())
        "implementation"(libs.findLibrary("androidx.core.ktx").get())
        "implementation"(libs.findBundle("androidx.lifecycle").get())
      }
    }
  }
}
