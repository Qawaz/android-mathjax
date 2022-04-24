package com.wakaztahir.mathjax

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.annotation.ColorInt
import com.wakaztahir.mathjax.awt.AndroidGraphics2D
import com.wakaztahir.mathjax.awt.Color
import org.scilab.forge.jlatexmath.ParseException
import org.scilab.forge.jlatexmath.TeXConstants
import org.scilab.forge.jlatexmath.TeXFormula
import org.scilab.forge.jlatexmath.TeXIcon

object JLatexMathBitmapBuilder {

    @Throws(ParseException::class)
    fun latexImageBitmap(
        context: Context,
        latex: String,
        bounds: Rect? = null,
        @ColorInt color: Int = android.graphics.Color.BLACK,
        @ColorInt backgroundColor: Int? = null,
        textSize: Float = 48f,
        insets: Rect? = null,
        alignment: LatexAlignment = LatexAlignment.Center
    ): Bitmap {
        JLatexMathAndroid.init(context)
        val icon = TeXFormula(latex).TeXIconBuilder()
            .setFGColor(Color(color))
            .setSize(textSize)
            .setStyle(TeXConstants.STYLE_DISPLAY)
            .build()
        val bitmap = Bitmap.createBitmap(icon.iconWidth, icon.iconHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawTeXIconIntoCanvas(
            context = context,
            icon = icon,
            bounds = bounds ?: Rect(0, 0, icon.iconWidth, icon.iconHeight),
            backgroundColor = backgroundColor,
            insets = insets,
            alignment = alignment,
            canvas = canvas
        )
        return bitmap
    }

    @Throws(ParseException::class)
    fun drawIntoCanvas(
        context: Context,
        latex: String,
        canvas: Canvas,
        bounds: Rect? = null,
        @ColorInt color: Int = android.graphics.Color.BLACK,
        @ColorInt backgroundColor: Int? = null,
        textSize: Float = 48f,
        insets: Rect? = null,
        alignment: LatexAlignment = LatexAlignment.Center,
    ): TeXIcon {
        JLatexMathAndroid.init(context)
        val icon = TeXFormula(latex).TeXIconBuilder()
            .setFGColor(Color(color))
            .setSize(textSize)
            .setStyle(TeXConstants.STYLE_DISPLAY)
            .build()
        drawTeXIconIntoCanvas(
            context = context,
            icon = icon,
            bounds = bounds ?: Rect(0, 0, icon.iconWidth, icon.iconHeight),
            backgroundColor = backgroundColor,
            insets = insets,
            alignment = alignment,
            canvas = canvas
        )
        return icon
    }

    @Throws(ParseException::class)
    fun drawTeXIconIntoCanvas(
        context: Context,
        icon: TeXIcon,
        bounds: Rect,
        @ColorInt backgroundColor: Int? = null,
        insets: Rect? = null,
        alignment: LatexAlignment = LatexAlignment.Center,
        canvas: Canvas,
    ) {
        JLatexMathAndroid.init(context)
        if (insets != null) {
            icon.insets = insets.toInsets()
        }

        val iconWidth = icon.iconWidth
        val iconHeight = icon.iconHeight

        val graphics2D = AndroidGraphics2D()

        val save: Int = canvas.save()
        try {

            // draw background before _possibly_ modifying latex (we should not modify background,
            //  it should always be the bounds we received)
            if (backgroundColor != null) {
                canvas.drawRect(bounds, Paint().apply { this.color = backgroundColor })
            }
            val w = bounds.width()
            val h = bounds.height()
            val scale: Float = if (iconWidth > w || iconHeight > h) {
                (w.toFloat() / iconWidth).coerceAtMost(h.toFloat() / iconHeight)
            } else {
                1f
            }
            val targetW: Int = (iconWidth * scale + 0.5f).toInt()
            val targetH: Int = (iconHeight * scale + 0.5f).toInt()
            val top = (h - targetH) / 2
            val left = when (alignment.value) {
                JLatexMathDrawable.ALIGN_CENTER -> (w - targetW) / 2
                JLatexMathDrawable.ALIGN_RIGHT -> w - targetW
                else -> 0
            }
            if (top != 0 || left != 0) {
                canvas.translate(left.toFloat(), top.toFloat())
            }
            if (scale.compareTo(1f) != 0) {
                canvas.scale(scale, scale)
            }
            graphics2D.setCanvas(canvas)
            icon.paintIcon(null, graphics2D, 0, 0)
        } finally {
            canvas.restoreToCount(save)
        }
    }
}