package ru.s4nchez.androidlearning

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment

class HeadlessFragment : Fragment(), HeadlessFragmentContract {

    private var isLoading: Boolean = false
    private var listener: HeadlessFragmentListener? = null
    private var result: Double? = null
    private var handler: Handler? = null

    override fun setListener(listener: HeadlessFragmentListener) {
        this.listener = listener
        result?.let { listener.onLoad(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        handler = Handler()
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

        isLoading = true
        Thread(Runnable {
            Thread.sleep(6000)
            result = Math.random()
            handler?.post { listener?.onLoad(result!!) }
            isLoading = false
        }).start()
    }
}