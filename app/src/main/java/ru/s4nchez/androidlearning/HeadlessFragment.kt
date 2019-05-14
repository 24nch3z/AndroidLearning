package ru.s4nchez.androidlearning

import android.os.Bundle
import android.support.v4.app.Fragment

class HeadlessFragment : Fragment(), HeadlessFragmentContract {

    private var isLoading: Boolean = false
    private var listener: HeadlessFragmentListener? = null
    private var result: Double? = null

    fun setListener(listener: HeadlessFragmentListener) {
        this.listener = listener
        result?.let { listener.onLoad(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onPause() {
        Logger.l("onPause")
        super.onPause()
    }

    override fun onStop() {
        Logger.l("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logger.l("onDestroy")
        super.onDestroy()
    }

    override fun load() {
        if (result != null) {
            listener?.onLoad(result!!)
            return
        }
        if (isLoading) {
            return
        }

        Thread(Runnable {
            Thread.sleep(6000)
            result = Math.random()
            listener?.onLoad(result!!)
        })
    }
}