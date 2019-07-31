package ru.s4nchez.androidlearning.database.dao

import androidx.room.Dao
import androidx.room.Insert
import ru.s4nchez.androidlearning.database.entity.Office

@Dao
interface OfficeDao {

    @Insert
    fun insert(office: Office): Long
}