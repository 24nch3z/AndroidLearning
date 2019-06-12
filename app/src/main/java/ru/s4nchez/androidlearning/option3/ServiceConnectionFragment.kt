package ru.s4nchez.androidlearning.option3

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_binding.*
import kotlinx.android.synthetic.main.fragment_main.start
import kotlinx.android.synthetic.main.fragment_main.stop
import ru.s4nchez.androidlearning.Logger
import ru.s4nchez.androidlearning.R

class ServiceConnectionFragment : Fragment(), ServiceContract {

    private lateinit var serviceConnection: ServiceConnection
    private var isBound = false
    private var service: ServiceConnectionService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                Logger.l("onServiceConnected")
                (binder as? ServiceConnectionService.MyBinder)?.let {
                    service = it.getService()
                    it.bindView(this@ServiceConnectionFragment)
                    isBound = true
                }
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Logger.l("onServiceDisconnected")
                isBound = false
                service = null
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_binding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start.setOnClickListener { activity?.let { it.startService(ServiceConnectionService.get(it)) } }
        stop.setOnClickListener { activity?.let { it.stopService(ServiceConnectionService.get(it)) } }
        bind.setOnClickListener { activity?.let { it.bindService(ServiceConnectionService.get(it), serviceConnection, Context.BIND_AUTO_CREATE) } }
        unbind.setOnClickListener {
            if (isBound) {
                activity?.unbindService(serviceConnection)
                isBound = false
            }
        }
        action.setOnClickListener { service?.run() }
    }

    override fun showResult(msg: String) {
        text.post { text.text = msg }
    }
}