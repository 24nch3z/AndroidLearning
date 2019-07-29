package ru.s4nchez.androidlearning

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.app.ActivityManager



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        kkk.setOnClickListener {
//            val keyguardManager = getSystemService(Activity.KEYGUARD_SERVICE) as KeyguardManager
//            val lock = keyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE)
//            lock.disableKeyguard()
//        }

//        Thread(Runnable {
//            val runtime = Runtime.getRuntime()
//            while (true) {
//                val res = runtime.totalMemory() - runtime.freeMemory()
//                Log.d("sssss", "running: $res")
//            }
//        }).start()

//        Toast.makeText(this, Runtime.getRuntime().totalMemory().toString(), Toast.LENGTH_LONG).show()
//        Toast.makeText(this, "=====", Toast.LENGTH_LONG).show()

        val memoryClass = (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .memoryClass
//        Toast.makeText(this, memoryClass.toString(), Toast.LENGTH_LONG)
//                .show()

        Log.d("sssss", Runtime.getRuntime().totalMemory().toString())
        Log.d("sssss", memoryClass.toString())


        val b1: Byte = 3
        val b2: Byte = 6
        var b = b1 + b2
    }
}
