package ru.s4nchez.androidlearning.option2

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import ru.s4nchez.androidlearning.Logger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BroadcastReceiverService : Service() {

    companion object {
        fun get(context: Context): Intent {
            return Intent(context, BroadcastReceiverService::class.java)
        }
    }

    lateinit var executor: ExecutorService

    override fun onCreate() {
        Logger.l("onCreate")
        super.onCreate()
        executor = Executors.newSingleThreadExecutor()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.l("onStartCommand")
        executor.execute {
            Thread.sleep(3000)
            sendBroadcast(Intent(BroadcastReceiverFragment.BROADCAST_TAG))
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Logger.l("onBind")
        return null
    }
}