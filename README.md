# Compose Math Jax

It uses a web view , When your latex changes , a typeset function is called inside the web view to
typeset the latex and convert it to svg format to display.

Moreover it uses Math Jax 3.2

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
    implementation 'com.github.timeline-notes:compose-mathjax:Tag'
}
```

## Usage

```kotlin
var latex by remember {
    mutableStateOf(
        "\$\$ x = y + 10 \$\$\n" +
                "\n" +
                "\$\$ 10 * 30 + 40 \\ frac{3}{4} sP/q \$\$"
    )
}

MathJax(
    latex = latex,
    color = MaterialTheme.colors.onBackground, //color of math jax
)

TextField(
    modifier = Modifier.fillMaxWidth(),
    value = latex,
    onValueChange = {
        latex = it
    },
    colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colors.onBackground)
)
```