package ru.s4nchez.androidlearning

import android.content.Context
import androidx.room.Room

object DI {

    private var appDatabase: AppDatabase? = null

    fun dataBase(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase::class.java, "database").build()
        }
        return appDatabase!!
    }
}