buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${property("agp.version")}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("kotlin.version")}")
    }
}