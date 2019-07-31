package ru.s4nchez.androidlearning.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.s4nchez.androidlearning.database.dao.OfficeDao
import ru.s4nchez.androidlearning.database.dao.PhoneDao
import ru.s4nchez.androidlearning.database.dao.UserDao
import ru.s4nchez.androidlearning.database.entity.Office
import ru.s4nchez.androidlearning.database.entity.Phone
import ru.s4nchez.androidlearning.database.entity.User

@Database(entities = [Phone::class, User::class, Office::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phoneDao(): PhoneDao
    abstract fun userDao(): UserDao
    abstract fun officeDao(): OfficeDao
}