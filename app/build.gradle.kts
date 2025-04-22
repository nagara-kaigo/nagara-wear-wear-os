import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.nagara_wear_wear_os"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nagara_wear_wear_os"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        val localProperties = Properties()
        localProperties.load(rootProject.file("local.properties").inputStream())

        buildConfigField("String", "OPENAI_API_KEY", "\"${localProperties["OPENAI_API_KEY"]}\"")
        buildConfigField("String", "API_LOGIN_ID", "\"${localProperties["API_LOGIN_ID"]}\"")
        buildConfigField("String", "API_PASSWORD", "\"${localProperties["API_PASSWORD"]}\"")
        buildConfigField("String", "API_BASE_URL", "\"${localProperties["API_BASE_URL"]}\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(libs.play.services.wearable)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.wear.tooling.preview)
    implementation(libs.activity.compose)
    implementation(libs.core.splashscreen)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.kotlinx.serialization.json)
}

