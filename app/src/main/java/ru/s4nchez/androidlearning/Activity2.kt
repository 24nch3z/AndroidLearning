package ru.s4nchez.androidlearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.l("Activity 2: onCreate")
        super.onCreate(savedInstanceState)
        finish()
    }

    override fun onStart() {
        Logger.l("Activity 2: onStart")
        super.onStart()
    }

    override fun onResume() {
        Logger.l("Activity 2: onResume")
        super.onResume()
    }

    override fun onRestart() {
        Logger.l("Activity 2: onRestart")
        super.onRestart()
    }

    override fun onPause() {
        Logger.l("Activity 2: onPause")
        super.onPause()
    }

    override fun onStop() {
        Logger.l("Activity 2: onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logger.l("Activity 2: onDestroy")
        super.onDestroy()
    }
}