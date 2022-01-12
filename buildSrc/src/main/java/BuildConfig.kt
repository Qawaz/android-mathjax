object BuildConfig {
    object Info {
        const val group = "com.wakaztahir"
        const val version = "2.0.3"
        const val versionCode = 2
        const val artifactId = "mathjax"

        const val ComposeVersion = "1.1.0-rc01"
        const val KotlinVersion = "1.6.0"
    }

    object Android {
        const val minSdkVersion = 21
        const val compileSdkVersion = 31
        const val targetSdkVersion = 31
    }

    object Dependencies {
        object Compose {
            val runtime get() = "org.jetbrains.compose.runtime:runtime:${Info.ComposeVersion}"
            val foundation get() = "org.jetbrains.compose.foundation:foundation:${Info.ComposeVersion}"
            val material get() = "org.jetbrains.compose.material:material:${Info.ComposeVersion}"
        }
    }
}