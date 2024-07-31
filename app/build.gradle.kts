@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.simplejoke.android.application)
    alias(libs.plugins.simplejoke.android.hilt)
}

android {
    namespace = "com.beeeam.simplejoke"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":feature"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.bundles.network)
}