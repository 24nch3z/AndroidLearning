package ru.s4nchez.androidlearning

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val color = Color.BLACK
        val mode = PorterDuff.Mode.MULTIPLY

        setColor(image_right_bottom, color, mode)

        image_right_bottom.setOnClickListener {
            image_right_bottom.animate()
                    .setDuration(1000)
                    .rotation(Random.nextInt(1000).toFloat())
                    .alpha(Random.nextFloat())
                    .start()
        }
    }

    private fun setColor(imageView: ImageView, color: Int, mode: PorterDuff.Mode) {
        val imageLeftTopDrawable = imageView.drawable
        imageLeftTopDrawable.setColorFilter(color, mode)
    }
}
