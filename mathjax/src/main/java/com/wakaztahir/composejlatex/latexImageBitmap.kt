package com.wakaztahir.composejlatex


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import org.scilab.forge.jlatexmath.ParseException

/**
 * This converts [latex] to [ImageBitmap]
 * This can throw [ParseException] so make sure your latex works
 * You can use non composable version which requires context and catch the exception
 * before setting the image bitmap
 */
@Throws(ParseException::class)
@Composable
fun latexImageBitmap(
    latex: String,
    background: Color = Color.Unspecified,
    textSize: Float = 48f,
    color: Color = Color.Unspecified
) = latexImageBitmap(
    context = LocalContext.current,
    latex = latex,
    background = background,
    textSize = textSize,
    color = color
)

/**
 * This converts [latex] to [ImageBitmap]
 * This can throw [ParseException] so make sure your latex works
 */
@Throws(ParseException::class)
fun latexImageBitmap(
    context: Context,
    latex: String,
    background: Color = Color.Unspecified,
    textSize: Float = 48f,
    color: Color = Color.Unspecified
): ImageBitmap {

    JLatexMathAndroid.init(context)

    return JLatexMathDrawable.builder(latex).apply {
        if (background != Color.Unspecified) {
            background(background.toViewColor())
        }
        if (color != Color.Unspecified) {
            color(color.toViewColor())
        }
        textSize(textSize)
    }.build().toBitmap().asImageBitmap()
}