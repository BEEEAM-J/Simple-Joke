@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.simplejoke.android.data)
}

android {
    namespace = "com.beeeam.data"
}

dependencies {
    implementation(project(":domain"))
}