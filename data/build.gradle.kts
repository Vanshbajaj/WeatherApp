import com.google.protobuf.gradle.proto
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.protobuf")

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

    sourceSets {
        getByName("main") {
            proto {
                srcDir("src/main/proto")
            }
        }
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
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.android) // or the latest version
    kapt(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)

}
kapt {
    correctErrorTypes= true
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.0" // Adjust the version
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
//                create("kotlin") {
//                    option("lite")
//                }
            }
        }

    }
}

