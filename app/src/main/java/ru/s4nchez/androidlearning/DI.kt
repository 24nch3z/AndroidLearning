package ru.s4nchez.androidlearning

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.androidlearning.database.AppDatabase
import ru.s4nchez.androidlearning.database.entity.Phone
import ru.s4nchez.androidlearning.database.entity.User

object DI {

    private var appDatabase: AppDatabase? = null

    fun dataBase(context: Context): AppDatabase {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase::class.java, "database")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            Completable.fromAction {
                                DI.dataBase(context).userDao().insert(
                                        User(
                                                firstName = "Санчез",
                                                secondName = "Батурин",
                                                id = 1
                                        )
                                )
                                DI.dataBase(context).userDao().insert(
                                        User(
                                                firstName = "Опаньки",
                                                secondName = "Попаньки",
                                                id = 2
                                        )
                                )
                                DI.dataBase(context).phoneDao().insert(
                                        Phone(
                                                number = "007",
                                                userId = 1
                                        )
                                )
                                DI.dataBase(context).phoneDao().insert(
                                        Phone(
                                                number = "008",
                                                userId = 1
                                        )
                                )
                                DI.dataBase(context).phoneDao().insert(
                                        Phone(
                                                number = "123",
                                                userId = 2
                                        )
                                )
                                DI.dataBase(context).phoneDao().insert(
                                        Phone(
                                                number = "666",
                                                userId = 2
                                        )
                                )
                            }.subscribeOn(Schedulers.io()).subscribe()
                        }
                    })
                    .build()
        }
        return appDatabase!!
    }
}