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

    val binder = MyBinder()
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
        return binder
    }

    override fun onRebind(intent: Intent?) {
        Logger.l("onRebind")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Logger.l("onUnbind")
        binder.unbind()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Logger.l("onDestroy")
        binder.unbind()
        super.onDestroy()
    }

    fun run() {
        executor.execute {
            Thread.sleep(3000)
            Logger.l("After run")
            binder.view?.showResult("Execute Complete")
        }
    }

    inner class MyBinder : Binder() {

        var view: ServiceContract? = null

        fun getService(): ServiceConnectionService {
            return this@ServiceConnectionService
        }

        fun bindView(view: ServiceContract) {
            this.view = view
        }

        fun unbind() {
            this.view = null
        }
    }
}