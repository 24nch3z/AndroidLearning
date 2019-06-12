package ru.s4nchez.androidlearning.option3

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import ru.s4nchez.androidlearning.Logger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ServiceConnectionService : Service() {

    companion object {
        fun get(context: Context): Intent {
            return Intent(context, ServiceConnectionService::class.java)
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
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Logger.l("onBind")
        return Binder()
    }

    override fun onRebind(intent: Intent?) {
        Logger.l("onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Logger.l("onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Logger.l("onDestroy")
        super.onDestroy()
    }
}