package ru.s4nchez.androidlearning

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Phone(

        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,

        val number: String
)