package ru.s4nchez.androidlearning.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,

        val firstName: String,

        val secondName: String
)