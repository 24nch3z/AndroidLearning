package ru.s4nchez.androidlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = User("Bob", 12, 2)
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val json = gson.toJson(user)
        json_view.text = json
    }
}
