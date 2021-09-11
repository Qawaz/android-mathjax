package com.wakaztahir.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.wakaztahir.example.ui.theme.ComposeMathJaxTheme
import com.wakaztahir.mathjax.MathJax

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMathJaxTheme {
                Column {

                    var latex by remember {
                        mutableStateOf(
                            "\$\$ x = y + 10 \$\$\n" +
                                    "\n" +
                                    "\$\$ 10 * 30 + 40 \\ frac{3}{4} sP/q \$\$"
                        )
                    }

                    MathJax(
                        latex = latex,
                        color = MaterialTheme.colors.onBackground,
                    )

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = latex,
                        onValueChange = {
                            latex = it
                        },
                        colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colors.onBackground)
                    )
                }
            }
        }
    }
}