package com.example.dailybriefing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainViewModel : ViewModel(){
    private var _timeValue = MutableLiveData<String>()
    private var _dateValue = MutableLiveData<String>()


    val timeValue: LiveData<String>
        get() = _timeValue

    val dateValue: LiveData<String>
        get() = _dateValue

    init {
        viewModelScope.launch {
            while (true) {
                _timeValue.value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("a h:m:ss"))
                _dateValue.value = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 M월 d일"))
                delay(1000)
            }
        }
    }
}