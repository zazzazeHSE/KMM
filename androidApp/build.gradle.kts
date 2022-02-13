plugins {
    id("com.android.application")
    kotlin("android")
}
val compose_version = "1.2.0-alpha02"
val koin_version = "3.1.2"
android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.schedules.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("io.insert-koin:koin-android:$koin_version")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha02")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.0-alpha02")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")
}
