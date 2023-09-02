@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.paparazzi)
    id("com.google.gms.google-services")
}

android {
    namespace = "no.app.features"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {
    // Project Modules
    implementation(project(mapOf("path" to ":data")))

    // Compose & UI Libraries
    implementation(platform(libs.compose.bom))
    implementation(libs.activity.compose)
    implementation(libs.material3)
    implementation(libs.paging.compose)
    implementation(libs.coil)
    implementation(libs.ui.tooling.preview)

    // Testing Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // Dependency Injection - Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)

    // Navigation & Destinations
    implementation(libs.destinations.core)
    ksp(libs.destinations.ksp)

    // UI Testing - Paparazzi
    implementation(libs.paparazzi.gradle.plugin) {
        isTransitive = false
    }

    // Firebase Libraries
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.play.services.auth)

    // Coroutine Testing Libraries
    testImplementation(libs.turbine)
    testImplementation(libs.couroutines.test)
}
