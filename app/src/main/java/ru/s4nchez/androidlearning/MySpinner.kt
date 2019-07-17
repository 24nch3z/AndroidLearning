package ru.s4nchez.androidlearning

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator

class MySpinner(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DEFAULT_CELL_SIZE = 20.0f
        private const val MIN_GAP_SIZE = 6.0f
        private const val MAX_GAP_SIZE = 20.0f
        private const val DIMENSION = 3
        private const val DEFAULT_CELL_COLOR = "#FFFF0000"
        private const val ROTATE_OFFSET = DEFAULT_CELL_SIZE * 2
        private const val ROTATE_ANGLE_STEP = 8.0f
        private val START_CELL_COLOR = Color.parseColor("#FFFF0000")
        private val END_CELL_COLOR = Color.parseColor("#FF0000FF")
    }

    private val cellRectF = RectF()
    private val cellPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor(DEFAULT_CELL_COLOR)
    }

    private var rotateAngle = 0.0f
    private var gapSize = MAX_GAP_SIZE
    private var gapStep = 0.1f
    private var currentColor = START_CELL_COLOR

    private var isStopped = false
    private var animationsParity = true

    private val colorAnimator = ValueAnimator.ofArgb(START_CELL_COLOR, END_CELL_COLOR).apply {
        duration = 2000
        interpolator = AccelerateInterpolator()
        addUpdateListener { currentColor = it.animatedValue as Int }
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (isStopped) return

                if (animationsParity) {
                    setIntValues(END_CELL_COLOR, START_CELL_COLOR)
                } else {
                    setIntValues(START_CELL_COLOR, END_CELL_COLOR)
                }
                animationsParity = !animationsParity
                start()
            }
        })
    }

    init {
        colorAnimator.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultViewSize = calculateViewSize()
        val measureWidth = resolveSize(calculateDesiredWidth(defaultViewSize), widthMeasureSpec)
        val measureHeight = resolveSize(calculateDesiredHeight(defaultViewSize), heightMeasureSpec)
        setMeasuredDimension(measureWidth, measureHeight)
    }

    private fun calculateViewSize(): Float {
        return DEFAULT_CELL_SIZE * DIMENSION + MAX_GAP_SIZE * (DIMENSION - 1) + ROTATE_OFFSET
    }

    private fun calculateDesiredWidth(defaultViewSize: Float): Int {
        return Math.max(suggestedMinimumWidth, defaultViewSize.toInt()) + paddingLeft + paddingRight
    }

    private fun calculateDesiredHeight(defaultViewSize: Float): Int {
        return Math.max(suggestedMinimumHeight, defaultViewSize.toInt()) + paddingTop + paddingBottom
    }

    override fun onDraw(canvas: Canvas) {
        val viewSize = calculateViewSize()
        val halfViewSize = viewSize / 2

        canvas.rotate(rotateAngle, halfViewSize, halfViewSize)
        calculateRotateAngle()
        calculateGapSize()

        cellPaint.color = currentColor
        for (i in 0 until DIMENSION) {
            for (j in 0 until DIMENSION) {
                if (i != 1 || j != 1) {
                    drawCell(
                            canvas = canvas,
                            i = i,
                            j = j,
                            cellSize = DEFAULT_CELL_SIZE,
                            cellGap = gapSize
                    )
                }
            }
        }
        drawCircle(canvas, viewSize, DEFAULT_CELL_SIZE / 2, gapSize)
        invalidate()
    }

    private fun calculateRotateAngle() {
        rotateAngle += ROTATE_ANGLE_STEP
        if (rotateAngle >= 360.0f) rotateAngle = 0.0f
    }

    private fun calculateGapSize() {
        gapSize += gapStep
        if ((gapStep > 0 && gapSize >= MAX_GAP_SIZE) ||
                (gapStep < 0 && gapSize <= MIN_GAP_SIZE)) {
            gapStep *= -1
        }
    }

    private fun drawCell(canvas: Canvas, i: Int, j: Int, cellSize: Float, cellGap: Float) {
        val start = width / 2 - cellSize - cellSize / 2 - cellGap
        cellRectF.top = start + i * cellSize + i * cellGap
        cellRectF.bottom = cellRectF.top + cellSize
        cellRectF.left = start + j * cellSize + j * cellGap
        cellRectF.right = cellRectF.left + cellSize
        canvas.drawRect(cellRectF, cellPaint)
    }

    private fun drawCircle(canvas: Canvas, viewSize: Float, radius: Float, gapSize: Float) {
        canvas.drawCircle(viewSize / 2, viewSize / 2, radius + gapSize - MIN_GAP_SIZE, cellPaint)
    }

    override fun onDetachedFromWindow() {
        isStopped = true
        colorAnimator.cancel()
        super.onDetachedFromWindow()
    }
}