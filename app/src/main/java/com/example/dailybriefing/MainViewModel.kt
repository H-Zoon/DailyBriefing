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
import java.util.*

class MainViewModel : ViewModel(){

    var dateValue = ObservableField<String>()
    var weekValue = ObservableField<String>()
    var timeValue = ObservableField<String>()
    var meridiemValue = ObservableField<String>()
    private var secView = true


    init {
        viewModelScope.launch {
            while (true) {
                dateValue.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.M.d")))
                weekValue.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern(" E").withLocale(
                    Locale.forLanguageTag("en"))))

                meridiemValue.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("a").withLocale(
                    Locale.forLanguageTag("en"))))

                secView = if(secView){
                    timeValue.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("h mm")))
                    false
                }else{
                    timeValue.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm")))
                    true
                }

                delay(1000)


            }
        }
    }


}