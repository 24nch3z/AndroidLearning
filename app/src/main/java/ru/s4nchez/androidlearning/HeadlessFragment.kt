package ru.s4nchez.androidlearning

import android.os.Bundle
import android.support.v4.app.Fragment

class HeadlessFragment : Fragment(), HeadlessFragmentContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun start(count: Int) {
        if (count < 0) return
        Thread(Runnable {
            for (i in count downTo 0) {
                Thread.sleep(1000)
                Logger.l(i.toString())
            }
        }).start()
    }
}