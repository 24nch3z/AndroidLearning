package ru.s4nchez.androidlearning

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

//        @Expose
        @SerializedName("userName")
        val name: String,

//        @Expose
        @SerializedName("userAge")
        val age: Int,

//        @Expose
        @SerializedName("userEyesNumber")
        val eyesNumber: Int
)