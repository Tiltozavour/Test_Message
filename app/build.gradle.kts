plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.test_message"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test_message"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    //Base
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //Retrofit
    implementation(libs.retrofit)
    implementation (libs.retrofit2.converter.gson)
    //OKKHTTP
    implementation (libs.okhttp)
    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    //Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //Number
    implementation(libs.ccp)
    //DI
    implementation (libs.dagger)
    kapt (libs.dagger.compiler)

    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}