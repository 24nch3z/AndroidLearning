package ru.s4nchez.androidlearning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val simpleLiveData = MutableLiveData<Boolean>()
    val singleLiveData = SingleLiveEvent<Boolean>()

    fun setValue() {
        simpleLiveData.setValue(true)
        singleLiveData.setValue(true)
    }
}