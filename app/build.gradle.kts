plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android.extensions")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "com.ibrahim.ny_times_demo"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Versions.code
        versionName = Versions.name

        multiDexEnabled = true


        //Base Url
        buildConfigField ("String",  "BASEURL",      "\"http://api.nytimes.com/svc/mostpopular/v2/\"")
        buildConfigField ("String",  "API_KEY",      "\"HIGO0C3HmyRYfOVgexDnMpyKA46fmDAW\"")

        testInstrumentationRunner = Tests.androidJUnitRunner
    }

    buildFeatures.viewBinding = true


    buildTypes {
        getByName("debug"){
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
        getByName("release"){
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    implementation(Dependencies.kotlin)

    implementation (Androidx.core)
    implementation (Androidx.activity)
    implementation (Androidx.fragment)
    implementation (Androidx.appcompat)
    implementation (Androidx.material)
    implementation (Androidx.constraintlayout)
    implementation (Androidx.livedata)
    implementation (Androidx.lifecycle_viewmodel_ktx)
    implementation (Androidx.extensions)
    implementation (Androidx.lifecycle_extensions)
    implementation (Androidx.legacy_support_v4)

    //Recyclerview
    implementation (Dependencies.recyclerview)

    //Glide
    implementation (Dependencies.glide)

    //Hilt
    implementation (Hilt.hilt_android)
    kapt(Hilt.hilt_compiler)
    kapt(Hilt.hilt_android_compiler)

    //Navigation view
    implementation (NavigationView.navigation_fragment)
    implementation (NavigationView.navigation_ui)

    //Retrofit
    implementation (Retrofit.retrofit)
    implementation (Retrofit.gson_converter)

    //Coroutines
    implementation (Coroutines.core)
    implementation (Coroutines.android)

    testImplementation(Tests.junit)
    androidTestImplementation (Tests.ext_junit)
    androidTestImplementation (Tests.espresso_core)
}