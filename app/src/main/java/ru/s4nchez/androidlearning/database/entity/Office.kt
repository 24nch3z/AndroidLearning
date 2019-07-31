package ru.s4nchez.androidlearning.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Office(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,

        val name: String,

        @Embedded
        val location: Location
)