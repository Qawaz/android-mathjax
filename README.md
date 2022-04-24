# Compose Math Jax

It uses JLatexMath , Its a fork of [noties/jlatexmath-android](https://github.com/noties/jlatexmath-android)
It does not have any dependencies except on
## Demo

![Screenshot_1631722134](https://user-images.githubusercontent.com/42442700/133469585-bb5a0a9e-5c47-4cda-806a-054c7a9ef22d.png)

## Setup

#### Get The Dependency

Use Github Packages to get the library [here](https://github.com/timeline-notes/compose-mathjax/packages/1191711)

#### Step 1 : Add the Github Packages Repo

This is kotlin gradle script

```kotlin

val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/timeline-notes/timeline-kmp")
    credentials {
        username = (githubProperties["gpr.usr"] ?: System.getenv("GPR_USER")).toString()
        password = (githubProperties["gpr.key"] ?: System.getenv("GPR_API_KEY")).toString()
    }
}
```

#### Step 2 : Create Github Properties File

Create `github.properties` file in your project at root level and add two properties (make github personal access token)

```properties
gpr.usr=yourusername
gpr.key=yourgithubpersonalaccesstoken
```

#### Step 3 : Add The Dependency

```kotlin
implementation("com.wakaztahir:mathjax:<current-version>")
```

or groovy

```groovy
implementation "com.wakaztahir:mathjax:<current-version>"
```

#### Additional

These dependencies will add the assets required for Cyrillic & Greek symbols , I haven't really tested the projects but
it should work just fine considering those assets will available in the context

```groovy
// for Cyrillic symbols
implementation 'ru.noties:jlatexmath-android-font-cyrillic:v0.2.0'

// for Greek symbols 
implementation 'ru.noties:jlatexmath-android-font-greek:v0.2.0'
```

## Usage

#### Get Android Bitmap

```kotlin
val bitmap = JLatexMathBitmapBuilder.latexImageBitmap(
    context = context,
    latex = latex,
    alignment = LatexAlignment.Start,
    color = textColor.toArgb(),
    backgroundColor = backgroundColor.toArgb(),
)
```

#### Using Jetpack Compose

```kotlin
var latex by remember { mutableStateOf(latexString) }
val context = LocalContext.current
val textColor = MaterialTheme.colors.onBackground
val backgroundColor = MaterialTheme.colors.background
var imageBitmap by remember {
    mutableStateOf(
        JLatexMathBitmapBuilder.latexImageBitmap(
            context = context,
            latex = latex,
            alignment = LatexAlignment.Start,
            color = textColor.toArgb(),
            backgroundColor = backgroundColor.toArgb(),
        ).asImageBitmap()
    )
}

Column(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
    Image(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        bitmap = imageBitmap,
        contentDescription = null
    )
    OutlinedTextField(
        value = latex,
        onValueChange = {
            latex = it
            currentJob?.cancel()
            currentJob = lifecycleScope.launch {
                try {
                    imageBitmap = JLatexMathBitmapBuilder.latexImageBitmap(
                        context = context,
                        latex = latex,
                        alignment = LatexAlignment.Start,
                        color = textColor.toArgb(),
                        backgroundColor = backgroundColor.toArgb()
                    ).asImageBitmap()
                } catch (_: ParseException) {

                }
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = textColor)
    )
}
```

##### Transparent Background
Keep background null and it won't draw the background !

##### Padding
There's insets parameter on functions and you can use a Rect
to apply padding from all sides

##### Bounds
You can control the size of the latex being drawn on bitmap
There's a bounds parameter in which you can specify right and bottom
parameters which are width and height respectively while keeping
left and top = 0

##### Drawing Into Canvas
If you have a canvas JLatexMathBitmapBuilder has a function
to draw into canvas , If you'd like to know the size of the width and height
of latex , you should convert it to TeXIcon and get width and height
copy the code from `drawIntoCanvas` function and get the width and height

##### Bitmap Size
To control bitmap size , You should create TeXIcon first , with latex (done in `drawIntoCanvas`) function , After
creating TeXIcon , you can use it to get the width and height of latex !
Create a bitmap using `Bitmap.createBitmap` function with the desired width and height
Create a canvas and supply this bitmap as a parameter into constructor
Use `JLatexMathBitmapBuilder.drawIntoCanvas` supply icon and canvas
to draw that icon into the canvas !