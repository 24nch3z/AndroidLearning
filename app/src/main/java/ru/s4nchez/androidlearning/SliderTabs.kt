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
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator

class SliderTabs(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DEFAULT_SLIDER_COLOR_HEX = "#FFFFFFFF"
        private const val DEFAULT_BG_COLOR_HEX = "#FFEAE9F0"
        private const val DEFAULT_SLIDER_POSITION = 1
        private const val DEFAULT_CORNERS_RADIUS = 60.0f
        private const val DEFAULT_TEXT_SIZE = 48.0f
        private const val DEFAULT_TEXT_COLOR = "#FF333333"
    }

    private var sliderPosition = DEFAULT_SLIDER_POSITION
    private lateinit var options: Array<CharSequence>
    private var sliderBackgroundColor: Int = 0
    private var sliderColor: Int = 0
    private var cornersRadius = 0.0f

    private val backgroundRectF = RectF()
    private val rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val sliderRectF = RectF()
    private val sliderRectInset = 4.0f

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var animSliderLeftSideXOffset = 0.0f
    private var animSliderRightSideXOffset = 0.0f
    private var isAnimate = false

    init {
        attrs?.let { consumeAttributeSet(context, it) }
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
            sliderPosition = typedArray.getInteger(
                    R.styleable.SliderTabs_st_defaultSliderOption,
                    DEFAULT_SLIDER_POSITION
            )
            if (sliderPosition > options.size) {
                sliderPosition = DEFAULT_SLIDER_POSITION
            }
            cornersRadius = typedArray.getDimension(
                    R.styleable.SliderTabs_st_cornersRadius,
                    DEFAULT_CORNERS_RADIUS
            )
            textPaint.color = typedArray.getColor(
                    R.styleable.SliderTabs_st_textColor,
                    Color.parseColor(DEFAULT_TEXT_COLOR)
            )
            textPaint.textSize = typedArray.getDimension(
                    R.styleable.SliderTabs_st_textSize,
                    DEFAULT_TEXT_SIZE
            )
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
        canvas.drawRoundRect(backgroundRectF, cornersRadius, cornersRadius, rectPaint)
    }

    private fun drawSlider(canvas: Canvas) {
        calculateSliderRectF()
        rectPaint.color = sliderColor
        canvas.drawRoundRect(sliderRectF, cornersRadius, cornersRadius, rectPaint)
    }

    private fun calculateSliderRectF() {
        sliderRectF.left = calculateSliderRectLeft(sliderPosition)
        sliderRectF.top = calculateSliderRectTop()
        sliderRectF.right = calculateSliderRectRight(sliderPosition)
        sliderRectF.bottom = calculateSliderRectBottom()
    }

    private fun calculateSliderRectLeft(sliderPosition: Int): Float {
        return (sliderPosition - 1) * tabWidth() + sliderRectInset - animSliderLeftSideXOffset
    }

    private fun calculateSliderRectRight(sliderPosition: Int): Float {
        return sliderPosition * tabWidth() - sliderRectInset - animSliderRightSideXOffset
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
        val startX = (sliderPosition - 1) * tabWidth()
        return tabWidth().toFloat() / 2 - textWidth / 2 + startX
    }

    private fun calculateTabTextY(): Float {
        return height / 2 - (textPaint.descent() + textPaint.ascent()) / 2
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isAnimate) return true
        var isEventHandled = false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> isEventHandled = true
            MotionEvent.ACTION_UP -> {
                handleClick(event.x)
                performClick()
                isEventHandled = true
            }
            MotionEvent.ACTION_CANCEL -> isEventHandled = true
        }
        return isEventHandled
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    private fun handleClick(x: Float) {
        val newSliderPosition = getSliderPositionByXOffset(x)
        if (sliderPosition != newSliderPosition) {
            val positionDiff = newSliderPosition - sliderPosition
            sliderPosition = newSliderPosition

            animSliderLeftSideXOffset = (tabWidth() * positionDiff).toFloat()
            animSliderRightSideXOffset = animSliderLeftSideXOffset

            val animatorLeft = ValueAnimator.ofFloat(animSliderLeftSideXOffset, 0.0f)
            animatorLeft.duration = 300
            if (positionDiff < 0) {
                animatorLeft.interpolator = AccelerateDecelerateInterpolator()
            } else {
                animatorLeft.interpolator = AccelerateInterpolator()
            }
            animatorLeft.addUpdateListener {
                animSliderLeftSideXOffset = it.animatedValue as Float
                invalidate()
            }
            animatorLeft.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    isAnimate = false
                }
            })
            animatorLeft.start()

            val animatorRight = ValueAnimator.ofFloat(animSliderRightSideXOffset, 0.0f)
            animatorRight.duration = 300
            if (positionDiff < 0) {
                animatorRight.interpolator = AccelerateInterpolator()
            } else {
                animatorRight.interpolator = AccelerateDecelerateInterpolator()
            }
            animatorRight.addUpdateListener { animSliderRightSideXOffset = it.animatedValue as Float }
            animatorRight.start()
            isAnimate = true
            invalidate()
        }
    }

    private fun getSliderPositionByXOffset(x: Float): Int {
        return (x / tabWidth()).toInt() + 1
    }

    private fun tabWidth() = width / options.size
}