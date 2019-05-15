package ru.s4nchez.androidlearning.headless

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import ru.s4nchez.androidlearning.Logger

class HeadlessFragment : Fragment(), HeadlessFragmentContract {

    private var isLoading: Boolean = false
    private var listener: HeadlessFragmentListener? = null
    private var result: Double? = null
    private var handler: Handler? = null

    override fun setListener(listener: HeadlessFragmentListener) {
        this.listener = listener
//        result?.let { listener.onLoad(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.l("onCreate")
        super.onCreate(savedInstanceState)
        retainInstance = true
        handler = Handler()
    }

    override fun onStart() {
        Logger.l("onStart")
        super.onStart()
    }

    override fun onResume() {
        Logger.l("onResume")
        super.onResume()
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
        this.listener = null
        this.handler?.removeCallbacksAndMessages(null)
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
            Logger.l("Load Success")
            result = Math.random()
            handler?.post { listener?.onLoad(result!!) }
            isLoading = false
        }).start()
    }
}