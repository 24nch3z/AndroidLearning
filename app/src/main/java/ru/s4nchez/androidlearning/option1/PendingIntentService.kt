package ru.s4nchez.androidlearning.option1

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import ru.s4nchez.androidlearning.Logger
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PendingIntentService : Service() {

    companion object {
        private const val ARG_PI = "pi"

        fun get(context: Context, pi: PendingIntent): Intent {
            return Intent(context, PendingIntentService::class.java).apply {
                putExtra(ARG_PI, pi)
            }
        }
    }

    lateinit var executor: ExecutorService

    override fun onCreate() {
        Logger.l("onCreate")
        super.onCreate()
        executor = Executors.newFixedThreadPool(1)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.l("onStartCommand")
        executor.execute {
            Thread.sleep(3000)
            val pi = intent?.getParcelableExtra<PendingIntent>(ARG_PI)
            pi?.send()
            Logger.l("End of job")
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Logger.l("onBind")
        return null
    }
}