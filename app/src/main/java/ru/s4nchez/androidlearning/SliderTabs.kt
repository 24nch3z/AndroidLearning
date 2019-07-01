package ru.s4nchez.androidlearning

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class SliderTabs(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DEFAULT_SLIDER_COLOR_HEX = "#FFFFFF"
        private const val DEFAULT_BG_COLOR_HEX = "#EAE9F0"
        private const val BACKGROUND_RECT_RADIUS = 60f
        private const val SLIDER_RECT_LEFT = 0f
        private const val SLIDER_RECT_TOP = 0f
        private const val TABS_COUNT = 2
    }

    private lateinit var leftTabText: String
    private lateinit var rightTabText: String
    private var _backgroundColor: Int = 0
    private var sliderColor: Int = 0

    private val backgroundRectF = RectF()
    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val sliderRectF = RectF()
    private val sliderRectInset = 4f
    private val sliderXOffset = 0f

    init {
        attrs?.let { consumeAttributeSet(context, it) }
    }

    private fun consumeAttributeSet(context: Context, attrs: AttributeSet) {
        val typedArray = context
                .theme
                .obtainStyledAttributes(attrs, R.styleable.SliderTabs, 0, 0)

        try {
            _backgroundColor = typedArray.getColor(
                    R.styleable.SliderTabs_st_backgroundColor,
                    Color.parseColor(DEFAULT_SLIDER_COLOR_HEX)
            )
            sliderColor = typedArray.getColor(
                    R.styleable.SliderTabs_st_sliderColor,
                    Color.parseColor(DEFAULT_BG_COLOR_HEX)
            )
            leftTabText = resolveLeftTabText(typedArray)
            rightTabText = resolveRightTabText(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun resolveLeftTabText(typedArray: TypedArray): String {
        val leftTabText = typedArray.getString(R.styleable.SliderTabs_st_leftTabText)
        return leftTabText ?: "Лева"
    }

    private fun resolveRightTabText(typedArray: TypedArray): String {
        val leftTabText = typedArray.getString(R.styleable.SliderTabs_st_rightTabText)
        return leftTabText ?: "Права"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureWidth = resolveSize(calculateDesiredWidth(), widthMeasureSpec)
        val measureHeight = resolveSize(calculateDesiredHeight(), heightMeasureSpec)
        setMeasuredDimension(measureWidth, measureHeight)
    }

    private fun calculateDesiredWidth(): Int {
        return suggestedMinimumWidth + paddingLeft + paddingRight
    }

    private fun calculateDesiredHeight(): Int {
        return suggestedMinimumHeight + paddingTop + paddingBottom
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (isViewVisible(w, h)) {
            calculateBackgroundRectF(w, h) // Возможно, параметры не нужны
            calculateSliderRectF(w, h)
        }
    }

    private fun isViewVisible(w: Int, h: Int) = w > 0 && h > 0

    private fun calculateBackgroundRectF(w: Int, h: Int) {
        backgroundRectF.left = 0f
        backgroundRectF.top = 0f
        backgroundRectF.right = w.toFloat()
        backgroundRectF.bottom = h.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        drawSlider(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        rectPaint.color = _backgroundColor
        canvas.drawRoundRect(backgroundRectF, BACKGROUND_RECT_RADIUS, BACKGROUND_RECT_RADIUS, rectPaint)
    }

    private fun drawSlider(canvas: Canvas) {
        rectPaint.color = sliderColor
        canvas.drawRoundRect(sliderRectF, BACKGROUND_RECT_RADIUS, BACKGROUND_RECT_RADIUS, rectPaint)
    }

    private fun calculateSliderRectF(w: Int, h: Int) {
        sliderRectF.left = calculateSliderRectLeft(w, h)
        sliderRectF.top = calculateSliderRectTop(w, h)
        sliderRectF.right = calculateSliderRectRight(w, h)
        sliderRectF.bottom = calculateSliderRectBottom(w, h)
    }

    private fun calculateSliderRectLeft(w: Int, h: Int): Float {
        return SLIDER_RECT_LEFT + sliderRectInset + sliderXOffset
    }

    private fun calculateSliderRectTop(w: Int, h: Int): Float {
        return SLIDER_RECT_TOP + sliderRectInset
    }

    private fun calculateSliderRectRight(w: Int, h: Int): Float {
        return w / TABS_COUNT - sliderRectInset + sliderXOffset
    }

    private fun calculateSliderRectBottom(w: Int, h: Int): Float {
        return h - sliderRectInset
    }
}