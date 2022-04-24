package com.wakaztahir.mathjax

import android.graphics.Rect
import com.wakaztahir.mathjax.awt.Insets

internal fun Rect.toInsets(): Insets = Insets(top, left, bottom, right)