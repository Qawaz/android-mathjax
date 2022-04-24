package com.wakaztahir.composemathjax

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.wakaztahir.composemathjax.ui.theme.ComposeMathjaxTheme
import com.wakaztahir.mathjax.JLatexMathBitmapBuilder
import com.wakaztahir.mathjax.LatexAlignment
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.scilab.forge.jlatexmath.ParseException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var currentJob: Job? = null
        var latexString = "\\begin{array}{l}"
        latexString += "\\forall\\varepsilon\\in\\mathbb{R}_+^*\\ \\exists\\eta>0\\ |x-x_0|\\leq\\eta\\Longrightarrow|f(x)-f(x_0)|\\leq\\varepsilon\\\\"
        latexString += "\\det\\begin{bmatrix}a_{11}&a_{12}&\\cdots&a_{1n}\\\\a_{21}&\\ddots&&\\vdots\\\\\\vdots&&\\ddots&\\vdots\\\\a_{n1}&\\cdots&\\cdots&a_{nn}\\end{bmatrix}\\overset{\\mathrm{def}}{=}\\sum_{\\sigma\\in\\mathfrak{S}_n}\\varepsilon(\\sigma)\\prod_{k=1}^n a_{k\\sigma(k)}\\\\"
        latexString += "\\sideset{_\\alpha^\\beta}{_\\gamma^\\delta}{\\begin{pmatrix}a&b\\\\c&d\\end{pmatrix}}\\\\"
        latexString += "\\int_0^\\infty{x^{2n} e^{-a x^2}\\,dx} = \\frac{2n-1}{2a} \\int_0^\\infty{x^{2(n-1)} e^{-a x^2}\\,dx} = \\frac{(2n-1)!!}{2^{n+1}} \\sqrt{\\frac{\\pi}{a^{2n+1}}}\\\\"
        latexString += "\\int_a^b{f(x)\\,dx} = (b - a) \\sum\\limits_{n = 1}^\\infty  {\\sum\\limits_{m = 1}^{2^n  - 1} {\\left( { - 1} \\right)^{m + 1} } } 2^{ - n} f(a + m\\left( {b - a} \\right)2^{-n} )\\\\"
        latexString += "\\int_{-\\pi}^{\\pi} \\sin(\\alpha x) \\sin^n(\\beta x) dx = \\textstyle{\\left \\{ \\begin{array}{cc} (-1)^{(n+1)/2} (-1)^m \\frac{2 \\pi}{2^n} \\binom{n}{m} & n \\mbox{ odd},\\ \\alpha = \\beta (2m-n) \\\\ 0 & \\mbox{otherwise} \\\\ \\end{array} \\right .}\\\\"
        latexString += "L = \\int_a^b \\sqrt{ \\left|\\sum_{i,j=1}^ng_{ij}(\\gamma(t))\\left(\\frac{d}{dt}x^i\\circ\\gamma(t)\\right)\\left(\\frac{d}{dt}x^j\\circ\\gamma(t)\\right)\\right|}\\,dt\\\\"
        latexString += "\\begin{array}{rl} s &= \\int_a^b\\left\\|\\frac{d}{dt}\\vec{r}\\,(u(t),v(t))\\right\\|\\,dt \\\\ &= \\int_a^b \\sqrt{u'(t)^2\\,\\vec{r}_u\\cdot\\vec{r}_u + 2u'(t)v'(t)\\, \\vec{r}_u\\cdot\\vec{r}_v+ v'(t)^2\\,\\vec{r}_v\\cdot\\vec{r}_v}\\,\\,\\, dt. \\end{array}\\\\"
        latexString += "\\end{array}"

        setContent {
            ComposeMathjaxTheme {
                Column {

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

                }
            }
        }
    }
}