package com.wakaztahir.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import com.wakaztahir.example.ui.theme.ComposeMathJaxTheme
import com.wakaztahir.mathjax.MathJax

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMathJaxTheme {
                Column {
                    MathJax(
                        latex = "$$ x = y + 10 $$",
                        color = MaterialTheme.colors.onBackground,
                    )
                }
            }
        }
    }
}