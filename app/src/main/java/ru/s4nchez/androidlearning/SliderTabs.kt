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

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        attrs?.let { consumeAttributeSet(context, it) }
        textPaint.color = Color.BLACK
        textPaint.textSize = 48f
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
        return leftTabText ?: "Левый"
    }

    private fun resolveRightTabText(typedArray: TypedArray): String {
        val leftTabText = typedArray.getString(R.styleable.SliderTabs_st_rightTabText)
        return leftTabText ?: "Правый"
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
            calculateBackgroundRectF()
            calculateSliderRectF()
        }
    }

    private fun isViewVisible(w: Int, h: Int) = w > 0 && h > 0

    private fun calculateBackgroundRectF() {
        backgroundRectF.left = 0f
        backgroundRectF.top = 0f
        backgroundRectF.right = width.toFloat()
        backgroundRectF.bottom = height.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        drawSlider(canvas)
        drawText(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        rectPaint.color = _backgroundColor
        canvas.drawRoundRect(backgroundRectF, BACKGROUND_RECT_RADIUS, BACKGROUND_RECT_RADIUS, rectPaint)
    }

    private fun drawSlider(canvas: Canvas) {
        rectPaint.color = sliderColor
        canvas.drawRoundRect(sliderRectF, BACKGROUND_RECT_RADIUS, BACKGROUND_RECT_RADIUS, rectPaint)
    }

    private fun calculateSliderRectF() {
        sliderRectF.left = calculateSliderRectLeft()
        sliderRectF.top = calculateSliderRectTop()
        sliderRectF.right = calculateSliderRectRight()
        sliderRectF.bottom = calculateSliderRectBottom()
    }

    private fun calculateSliderRectLeft(): Float {
        return SLIDER_RECT_LEFT + sliderRectInset + sliderXOffset
    }

    private fun calculateSliderRectTop(): Float {
        return SLIDER_RECT_TOP + sliderRectInset
    }

    private fun calculateSliderRectRight(): Float {
        return width / TABS_COUNT - sliderRectInset + sliderXOffset
    }

    private fun calculateSliderRectBottom(): Float {
        return height - sliderRectInset
    }

    private fun drawText(canvas: Canvas) {
        drawLeftTabText(canvas)
        drawRightTabText(canvas)
    }

    private fun drawLeftTabText(canvas: Canvas) {
        val textWidth = textPaint.measureText(leftTabText)
        canvas.drawText(
                leftTabText,
                calculateTabTextX(1, textWidth),
                calculateTabTextY(),
                textPaint
        )
    }

    private fun drawRightTabText(canvas: Canvas) {
        val textWidth = textPaint.measureText(rightTabText)
        canvas.drawText(
                rightTabText,
                calculateTabTextX(2, textWidth),
                calculateTabTextY(),
                textPaint
        )
    }

    private fun calculateTabTextX(tabsPosition: Int, textWidth: Float): Float {
        val tabWidth = width / TABS_COUNT
        val startX = (tabsPosition - 1) * tabWidth
        return tabWidth.toFloat() / 2 - textWidth / 2 + startX
    }

    private fun calculateTabTextY(): Float {
        return height / 2 - (textPaint.descent() + textPaint.ascent()) / 2
    }
}