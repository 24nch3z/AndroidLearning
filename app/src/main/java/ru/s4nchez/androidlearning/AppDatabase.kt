package ru.s4nchez.androidlearning

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Phone::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phoneDao(): PhoneDao
}