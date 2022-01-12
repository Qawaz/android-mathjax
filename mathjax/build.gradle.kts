import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    buildFeatures {
        compose = true
    }
    compileSdk = BuildConfig.Android.compileSdkVersion
    buildToolsVersion = "30.0.3"

    composeOptions {
//        kotlinCompilerVersion = BuildConfig.Info.ComposeVersion
        kotlinCompilerExtensionVersion = BuildConfig.Info.ComposeVersion
    }
    defaultConfig {
        minSdk = BuildConfig.Android.minSdkVersion
        targetSdk = BuildConfig.Android.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation("androidx.compose.ui:ui:${BuildConfig.Info.ComposeVersion}")
    implementation("androidx.core:core-ktx:1.6.0")
}

group = BuildConfig.Info.group
version = BuildConfig.Info.version

val githubProperties = Properties()
kotlin.runCatching {
    githubProperties.load(FileInputStream(rootProject.file("github.properties")))
}


afterEvaluate {
    publishing {
        repositories {
            maven {
                name = "GithubPackages"
                url = uri("https://maven.pkg.github.com/timeline-notes/compose-mathjax")

                credentials {
                    username = (githubProperties["gpr.usr"] ?: System.getenv("GPR_USER")).toString()
                    password = (githubProperties["gpr.key"] ?: System.getenv("GPR_API_KEY")).toString()
                }
            }
        }
    }
}