
import java.util.Properties

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")

    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use {
            load(it)
        }
    }
}

val mapsApiKey = localProperties.getProperty("MAPS_API_KEY", "")

val openChargeMapApiKey = localProperties.getProperty("OPEN_CHARGE_MAP_API_KEY", "")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.aksh.chargenearby"
    compileSdk {
        version = release(37) {
        }
    }

    defaultConfig {
        applicationId = "com.aksh.chargenearby"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey

        buildConfigField(
            "String",
            "OPEN_CHARGE_MAP_API_KEY",
            "\"$openChargeMapApiKey\""
        )
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
    buildFeatures {
        buildConfig = true
        compose = true

    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation("androidx.compose.material:material-icons-core")

    // hilt
    implementation("com.google.dagger:hilt-android:2.60")
    ksp("com.google.dagger:hilt-android-compiler:2.60")

    implementation("androidx.navigation:navigation-compose:2.9.8")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.11.0")


    implementation("androidx.hilt:hilt-navigation-compose:1.4.0")


    implementation("androidx.room:room-runtime:2.8.4")
    ksp("androidx.room:room-compiler:2.8.4")
    implementation("androidx.room:room-ktx:2.8.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.11.0")

    implementation("androidx.compose.material:material-icons-extended")

    implementation(libs.maps.compose)
    implementation(libs.play.services.location)

    implementation(libs.kotlinx.coroutines.play.services)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)





}