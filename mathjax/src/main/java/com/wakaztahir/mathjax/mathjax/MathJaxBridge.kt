package com.wakaztahir.mathjax.mathjax

import android.webkit.JavascriptInterface

class MathJaxBridge(
    val mathJax: MathJaxView,
    @get:JavascriptInterface val outputScale: Int,
    @get:JavascriptInterface val minScale: Int,
    val onRendered: () -> Unit,
) {

    @JavascriptInterface
    fun onRendered() = onRendered
}
