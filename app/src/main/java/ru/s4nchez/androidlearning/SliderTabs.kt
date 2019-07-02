package ru.s4nchez.androidlearning

import android.content.Context
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
        private const val DEFAULT_SLIDER_POSITION = 1
        private const val BACKGROUND_RECT_RADIUS = 60f
    }

    private var sliderPosition = DEFAULT_SLIDER_POSITION

    private lateinit var options: Array<CharSequence>

    private var sliderBackgroundColor: Int = 0
    private var sliderColor: Int = 0

    private val backgroundRectF = RectF()
    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val sliderRectF = RectF()
    private val sliderRectInset = 4f

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        attrs?.let { consumeAttributeSet(context, it) }
        textPaint.color = Color.parseColor("#FF444444")
        textPaint.textSize = 48f
    }

    private fun consumeAttributeSet(context: Context, attrs: AttributeSet) {
        val typedArray = context
                .theme
                .obtainStyledAttributes(attrs, R.styleable.SliderTabs, 0, 0)

        try {
            sliderBackgroundColor = typedArray.getColor(
                    R.styleable.SliderTabs_st_backgroundColor,
                    Color.parseColor(DEFAULT_SLIDER_COLOR_HEX)
            )
            sliderColor = typedArray.getColor(
                    R.styleable.SliderTabs_st_sliderColor,
                    Color.parseColor(DEFAULT_BG_COLOR_HEX)
            )
            options = typedArray.getTextArray(R.styleable.SliderTabs_st_options)
            sliderPosition = typedArray.getInteger(R.styleable.SliderTabs_st_defaultSliderOption, DEFAULT_SLIDER_POSITION)
            if (sliderPosition > options.size) sliderPosition = DEFAULT_SLIDER_POSITION
        } finally {
            typedArray.recycle()
        }
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
        drawOptionsText(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        rectPaint.color = sliderBackgroundColor
        canvas.drawRoundRect(backgroundRectF, BACKGROUND_RECT_RADIUS, BACKGROUND_RECT_RADIUS, rectPaint)
    }

    private fun drawSlider(canvas: Canvas) {
        calculateSliderRectF()
        rectPaint.color = sliderColor
        canvas.drawRoundRect(sliderRectF, BACKGROUND_RECT_RADIUS, BACKGROUND_RECT_RADIUS, rectPaint)
    }

    private fun calculateSliderRectF() {
        sliderRectF.left = calculateSliderRectLeft(sliderPosition)
        sliderRectF.top = calculateSliderRectTop()
        sliderRectF.right = calculateSliderRectRight(sliderPosition)
        sliderRectF.bottom = calculateSliderRectBottom()
    }

    private fun calculateSliderRectLeft(sliderPosition: Int): Float {
        val tabWidth = width / options.size
        return (sliderPosition - 1) * tabWidth + sliderRectInset
    }

    private fun calculateSliderRectRight(sliderPosition: Int): Float {
        val tabWidth = width / options.size
        return sliderPosition * tabWidth - sliderRectInset
    }

    private fun calculateSliderRectTop(): Float {
        return sliderRectInset
    }

    private fun calculateSliderRectBottom(): Float {
        return height - sliderRectInset
    }

    private fun drawOptionsText(canvas: Canvas) {
        options.forEachIndexed { index, text -> drawTextOption(index + 1, text, canvas) }
    }

    private fun drawTextOption(position: Int, text: CharSequence, canvas: Canvas) {
        val textWidth = textPaint.measureText(text.toString())
        canvas.drawText(
                text.toString(),
                calculateTabTextX(position, textWidth),
                calculateTabTextY(),
                textPaint
        )
    }

    private fun calculateTabTextX(sliderPosition: Int, textWidth: Float): Float {
        val tabWidth = width / options.size
        val startX = (sliderPosition - 1) * tabWidth
        return tabWidth.toFloat() / 2 - textWidth / 2 + startX
    }

    private fun calculateTabTextY(): Float {
        return height / 2 - (textPaint.descent() + textPaint.ascent()) / 2
    }
}