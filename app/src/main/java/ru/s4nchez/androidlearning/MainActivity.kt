package ru.s4nchez.androidlearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = LinearLayoutManager(this)
        manager.scrollToPosition(Int.MAX_VALUE / 4)
        with(recycler_view) {
            layoutManager = manager
            adapter = Adapter(31)
            LinearSnapHelper().attachToRecyclerView(this)
        }
    }
}
