package ru.s4nchez.androidlearning.option2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*
import ru.s4nchez.androidlearning.R

class BroadcastReceiverFragment : Fragment() {

    companion object {
        const val BROADCAST_TAG = "ru.s4nchez.androidlearning.option2.BroadcastReceiverFragment"
    }

    lateinit var broadcast: BroadcastReceiver

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start.setOnClickListener { activity?.let { it.startService(BroadcastReceiverService.get(it)) } }
        stop.setOnClickListener { activity?.let { it.stopService(BroadcastReceiverService.get(it)) } }
    }

    override fun onResume() {
        super.onResume()
        broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                text.text = "onReceive"
            }
        }
        activity?.registerReceiver(broadcast, IntentFilter(BROADCAST_TAG))
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(broadcast)
    }
}