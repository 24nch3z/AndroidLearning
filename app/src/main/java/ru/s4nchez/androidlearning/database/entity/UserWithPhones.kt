package ru.s4nchez.androidlearning.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithPhones(

        @Embedded
        val user: User,

        @Relation(parentColumn = "id", entityColumn = "user_id", entity = Phone::class)
        val phones: List<Phone>
)