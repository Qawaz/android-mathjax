# Compose Math Jax

It uses JLatexMath , Its a fork of [noties/jlatexmath-android](https://github.com/noties/jlatexmath-android)

## Setup

#### Step 1. Add the JitPack repository to your build file

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### Step 2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.timeline-notes:compose-mathjax:2.0.0'
}
```

#### Additional

These dependencies will add the assets required for Cyrillic & Greek symbols , I have't really tested the projects but it should work just fine
considering those assets will available in the context

```groovy
// for Cyrillic symbols
implementation 'ru.noties:jlatexmath-android-font-cyrillic:${version}'

// for Greek symbols 
implementation 'ru.noties:jlatexmath-android-font-greek:${version}'
```

## Usage

```kotlin
var latex by remember { mutableStateOf(latexString) }
val context = LocalContext.current
var imageBitmap by remember {
    mutableStateOf(latexImageBitmap(context, latex))
}

Column(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
    Image(
        bitmap = imageBitmap,
        contentDescription = null
    )
    TextField(
        value = latex,
        onValueChange = {
            latex = it
            kotlin.runCatching { latexImageBitmap(context = context, latex) }
                .getOrNull()?.let { bitmap ->
                    imageBitmap = bitmap
                }
        }
    )
}
```

## Demo

![Screenshot_1631722134](https://user-images.githubusercontent.com/42442700/133469585-bb5a0a9e-5c47-4cda-806a-054c7a9ef22d.png)
