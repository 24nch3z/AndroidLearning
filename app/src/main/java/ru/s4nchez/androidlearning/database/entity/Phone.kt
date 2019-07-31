package ru.s4nchez.androidlearning.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = User::class, childColumns = ["user_id"], parentColumns = ["id"], onDelete = CASCADE)
])
data class Phone(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,

        val number: String,

//        @ForeignKey(entity = Phone::class, childColumns = ["user_id"], parentColumns = ["id"], onDelete = CASCADE) // Не работает
        @ColumnInfo(name = "user_id")
        val userId: Long
)