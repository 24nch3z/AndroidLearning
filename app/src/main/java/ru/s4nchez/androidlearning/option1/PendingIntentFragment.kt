package ru.s4nchez.androidlearning.option1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.s4nchez.androidlearning.Logger
import ru.s4nchez.androidlearning.R

class PendingIntentFragment : Fragment() {

    companion object {
        const val REQUEST_CODE_PI_SERVICE = 41
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start.setOnClickListener { _ ->
            activity?.let {
                val pi = it.createPendingResult(REQUEST_CODE_PI_SERVICE, Intent(), 0)
                it.startService(PendingIntentService.get(it, pi))
            }
        }
        stop.setOnClickListener { }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Logger.l("Fragment onActivityResult")
        text.text = requestCode.toString()
    }
}