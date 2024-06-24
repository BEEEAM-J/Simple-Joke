@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.simplejoke.android.feature)
    alias(libs.plugins.simplejoke.android.hilt)
}

android {
    namespace = "com.beeeam.feature"
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.material)

    implementation(libs.kotlinx.coroutine)
}