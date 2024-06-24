import com.beeeam.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidDataConventionPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("simplejoke.android.library")
        apply("simplejoke.android.hilt")
      }

      dependencies {

        "implementation"(libs.findBundle("network").get())
        "implementation"(libs.findLibrary("kotlinx.coroutine").get())
      }
    }
  }
}
