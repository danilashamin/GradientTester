package com.chat.betrothal.single.service.gradienttest

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.cos
import kotlin.math.sin

class LinearGradientDrawable(
    colors: IntArray = intArrayOf(Color.WHITE, Color.BLACK),
    offsets: FloatArray? = null,
    angle: Float = 0f,
    cornersRadius: Int = 0,
    tileMode: Shader.TileMode = Shader.TileMode.CLAMP,
    private var gradientAlpha: Int = 255
) : Drawable() {

    private val matrix: Matrix = Matrix()

    private var rebuildGradient: Boolean = true
    private var gradientPaint: Paint = Paint()
    private var centerInit = false
    private var centerX: Float = 0.0f
    private var centerY: Float = 0.0f
    var scaleX: Float = 1.0f
    var scaleY: Float = 1.0f

    var colors: IntArray = colors
        set(colors) {
            field = colors
            rebuild()
        }
    var offsets: FloatArray? = offsets
        set(offsets) {
            field = offsets
            rebuild()
        }
    var angle: Float = angle
        set(angle) {
            field = angle
            rebuild()
        }
    var tileMode: Shader.TileMode = tileMode
        set(tileMode) {
            field = tileMode
            rebuild()
        }
    var cornersRadius: Int = cornersRadius
        set(cornersRadius) {
            field = cornersRadius
            rebuild()
        }
    var shader: Shader? = null

    private fun getPaint(width: Int, height: Int, forceRebuild: Boolean = false): Paint {
        if (rebuildGradient || forceRebuild) {
            if (!centerInit) {
                centerX = width / 2.0f
                centerY = height / 2.0f
                centerInit = true
            }

            matrix.setScale(scaleX, scaleY, centerX, centerY)
            val matrixWithoutRotate = Matrix(matrix)
            matrix.postRotate(angle, centerX, centerY)

            val angleInRadian = Math.toRadians(angle.toDouble())
            val w = cos(angleInRadian).toFloat() * width / 2
            val h = sin(angleInRadian).toFloat() * height / 2
            shader = LinearGradient(
                centerX - w,
                centerY - h,
                centerX + w,
                centerY + h,
                colors,
                offsets,
                tileMode
            )
            shader?.setLocalMatrix(matrixWithoutRotate)

            gradientPaint.reset()
            gradientPaint.shader = shader
            gradientPaint.alpha = gradientAlpha
            rebuildGradient = false
            return gradientPaint
        }
        return gradientPaint
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            bounds.width().toFloat(),
            bounds.height().toFloat(),
            cornersRadius.toFloat(),
            cornersRadius.toFloat(),
            getPaint(bounds.width(), bounds.height())
        )
    }

    private fun rebuild() {
        rebuildGradient = true
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        this.gradientAlpha = alpha
        rebuild()
    }

    private fun center(x: Float, y: Float) {
        centerX = x
        centerY = y
        centerInit = true
        rebuild()
    }

    fun scale(x: Float, y: Float)
    {
        scaleX = x
        scaleY = y
        rebuild()
    }

    override fun getOpacity(): Int {
        return when (gradientAlpha) {
            1 -> PixelFormat.OPAQUE
            0 -> PixelFormat.TRANSPARENT
            else -> PixelFormat.TRANSLUCENT
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        gradientPaint.colorFilter = colorFilter
    }
}