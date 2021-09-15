# Compose Math Jax

It uses JLatexMath , Its a fork of [noties/jlatexmath-android](https://github.com/noties/jlatexmath-android)

## Demo

![ezgif com-gif-maker](https://user-images.githubusercontent.com/42442700/132947732-9d302d3e-ab99-4a12-96ab-9ecec25bb359.gif)

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