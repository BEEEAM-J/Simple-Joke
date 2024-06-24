@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.simplejoke.android.library)
    alias(libs.plugins.simplejoke.android.data)
    alias(libs.plugins.simplejoke.android.hilt)
}

android {
    namespace = "com.beeeam.data"
}

dependencies {
    implementation(project(":domain"))
}