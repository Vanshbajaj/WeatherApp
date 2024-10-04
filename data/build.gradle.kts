import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // Make sure this is included
    id("dagger.hilt.android.plugin")
}

android {
    val apiPropertiesFile = rootProject.file("local.properties")
    val apiProperties = Properties().apply { load(FileInputStream(apiPropertiesFile)) }

    namespace = "com.open.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        // Add the API key to BuildConfig
        buildConfigField("String", "API_KEY", "\"${apiProperties["API_KEY"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // The API key is already added in the defaultConfig
            // You can add debug-specific configurations here if needed
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation("com.google.dagger:hilt-android:2.46") // or the latest version
    kapt("com.google.dagger:hilt-compiler:2.46")
    implementation(libs.logging.interceptor)
}
