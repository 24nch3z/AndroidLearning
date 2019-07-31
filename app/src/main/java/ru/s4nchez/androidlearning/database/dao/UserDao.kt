package ru.s4nchez.androidlearning.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import ru.s4nchez.androidlearning.database.entity.User
import ru.s4nchez.androidlearning.database.entity.UserWithPhones

@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Long

    @Query("SELECT * FROM User")
    fun getAll(): Observable<List<UserWithPhones>>

    @Delete
    fun delete(user: User): Completable

    @Query("DELETE FROM User WHERE id = :id")
    fun deleteByID(id: Long): Completable
}