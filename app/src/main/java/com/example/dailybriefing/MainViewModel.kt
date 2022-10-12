package com.example.dailybriefing

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel : ViewModel(){
    var timeValue = ObservableField<String>()
    var dateValue = ObservableField<String>()

    init {
        viewModelScope.launch {
            while (true) {
                timeValue.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("a h:m:ss")))
                dateValue.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 M월 d일")))
                delay(1000)
                Log.d("c", "${timeValue.get()}")
            }
        }
    }


}