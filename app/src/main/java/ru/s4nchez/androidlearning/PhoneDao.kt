package ru.s4nchez.androidlearning

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

@Dao
interface PhoneDao {

    @Insert
    fun insert(phone: Phone): Long

    @Query("SELECT * From Phone")
    fun getAll(): Observable<List<Phone>>

    @Query("DELETE FROM Phone")
    fun dropTable()
}