package ru.s4nchez.androidlearning

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val color = Color.BLACK
        val mode = PorterDuff.Mode.XOR

        setColor(image_left_top, color, mode)
        setColor(image_right_top, color, mode)
        setColor(image_left_bottom, color, mode)
        setColor(image_right_bottom, color, mode)
    }

    private fun setColor(imageView: ImageView, color: Int, mode: PorterDuff.Mode) {
        val imageLeftTopDrawable = imageView.drawable
        imageLeftTopDrawable.setColorFilter(color, mode)
    }
}
