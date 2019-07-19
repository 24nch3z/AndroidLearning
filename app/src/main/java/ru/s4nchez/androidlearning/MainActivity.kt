package ru.s4nchez.androidlearning

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by lazy { ViewModelProviders.of(this).get(MyViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.singleLiveData.observe(this, Observer {
            Toast.makeText(this, "Енотик", Toast.LENGTH_SHORT).show()
        })

        btn1.setOnClickListener { viewModel.setValue() }
    }
}
