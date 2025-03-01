plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "1.9.0" // Update to latest Kotlin version
}

android {
    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Core Ktor Client
    implementation(libs.ktor.client.core)

    // HTTP Engine (Choose One)
        // implementation(libs.ktor.client.okhttp) // ✅ Best for Android
     implementation(libs.ktor.client.cio) // ✅ Alternative lightweight option (Remove OkHttp if using this)

    // Logging & Debugging
    implementation(libs.ktor.client.logging)

    // JSON Handling
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Authentication
    implementation(libs.ktor.client.auth)

    // WebSockets
    implementation(libs.ktor.client.websockets)

    // Unit Testing (Keep only if needed)
    // testImplementation(libs.ktor.client.mock) // ✅ Remove if not writing unit tests
}
