plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = BuildConfig.Android.compileSdkVersion

    defaultConfig {
        applicationId = "com.wakaztahir.composemathjax"
        minSdk = BuildConfig.Android.minSdkVersion
        targetSdk = BuildConfig.Android.targetSdkVersion
        versionCode = BuildConfig.Info.versionCode
        versionName = BuildConfig.Info.version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildConfig.Info.ComposeVersion
//        kotlinCompilerVersion = BuildConfig.Info.KotlinVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    // Internal
    implementation(project(":mathjax"))

    // Android
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    // Android Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    with(BuildConfig.Info) {
        // Compose
        implementation("androidx.compose.ui:ui:${ComposeVersion}")
        implementation("androidx.compose.material:material:${ComposeVersion}")
        implementation("androidx.compose.ui:ui-tooling-preview:${ComposeVersion}")
        implementation("androidx.activity:activity-compose:1.4.0")

        // Compose Testing
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:${ComposeVersion}")
        debugImplementation("androidx.compose.ui:ui-tooling:${ComposeVersion}")
    }
}