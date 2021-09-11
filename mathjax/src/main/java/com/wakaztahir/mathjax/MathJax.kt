package com.wakaztahir.mathjax

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.wakaztahir.mathjax.mathjax.MathJaxView


@Composable
fun MathJax(
    modifier: Modifier = Modifier,
    latex: String,
    color: Color
) {

    val setProperties: MathJaxView.() -> Unit = {
        setInputText(latex)
        setTexColor("rgba(${color.red * 255},${color.green * 255},${color.blue * 255},${color.alpha})")
    }

    AndroidView(
        modifier = modifier,
        factory = {
            MathJaxView(it).apply {
                setOnLoadedListener {
                    setProperties()
                }
                setOnRenderedListener {
                    Log.d("BL_MathComponent", "Math Rendered")
                }
            }
        },
        update = {
            setProperties(it)
        }
    )
}