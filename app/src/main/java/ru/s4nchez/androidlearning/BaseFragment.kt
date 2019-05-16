package ru.s4nchez.androidlearning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val layout: Int
    abstract val TAG: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Logger.l("$TAG onCreateView")
        return inflater.inflate(layout, container, false)
    }

    override fun onStart() {
        Logger.l("$TAG onStart")
        super.onStart()
    }

    override fun onResume() {
        Logger.l("$TAG onResume")
        super.onResume()
    }

    override fun onPause() {
        Logger.l("$TAG onPause")
        super.onPause()
    }

    override fun onStop() {
        Logger.l("$TAG onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Logger.l("$TAG onDestroy")
        super.onDestroy()
    }
}