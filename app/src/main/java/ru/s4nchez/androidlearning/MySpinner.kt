package ru.s4nchez.androidlearning

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class MySpinner(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DEFAULT_CELL_SIZE = 20.0f
        private const val DEFAULT_GAP_SIZE = 12.0f
        private const val DIMENSION = 3
        private const val DEFAULT_CELL_COLOR = "#FFFF0000"
    }

    private val cellRectF = RectF()
    private val cellPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor(DEFAULT_CELL_COLOR)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultViewSize = DEFAULT_CELL_SIZE * DIMENSION + DEFAULT_GAP_SIZE * (DIMENSION - 1)
        val measureWidth = resolveSize(calculateDesiredWidth(defaultViewSize), widthMeasureSpec)
        val measureHeight = resolveSize(calculateDesiredHeight(defaultViewSize), heightMeasureSpec)
        setMeasuredDimension(measureWidth, measureHeight)
    }

    private fun calculateDesiredWidth(defaultViewSize: Float): Int {
        return Math.max(suggestedMinimumWidth, defaultViewSize.toInt()) + paddingLeft + paddingRight
    }

    private fun calculateDesiredHeight(defaultViewSize: Float): Int {
        return Math.max(suggestedMinimumHeight, defaultViewSize.toInt()) + paddingTop + paddingBottom
    }

    override fun onDraw(canvas: Canvas) {
        for (i in 0 until DIMENSION) {
            for (j in 0 until DIMENSION) {
                drawCell(
                        canvas = canvas,
                        i = i,
                        j = j,
                        cellSize = DEFAULT_CELL_SIZE,
                        cellGap = DEFAULT_GAP_SIZE
                )
            }
        }
    }

    private fun drawCell(canvas: Canvas, i: Int, j: Int, cellSize: Float, cellGap: Float) {
        cellRectF.top = i * cellSize + i * cellGap
        cellRectF.bottom = cellRectF.top + cellSize
        cellRectF.left = j * cellSize + j * cellGap
        cellRectF.right = cellRectF.left + cellSize
        canvas.drawRect(cellRectF, cellPaint)
    }
}