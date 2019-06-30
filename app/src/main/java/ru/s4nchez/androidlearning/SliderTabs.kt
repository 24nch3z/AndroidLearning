package ru.s4nchez.androidlearning

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class SliderTabs(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DEFAULT_SLIDER_COLOR_HEX = "#FFFFFF"
        private const val DEFAULT_BG_COLOR_HEX = "#EAE9F0"
    }

    private lateinit var leftTabText: String
    private lateinit var rightTabText: String
    private var _backgroundColor: Int = 0
    private var sliderColor: Int = 0

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
}