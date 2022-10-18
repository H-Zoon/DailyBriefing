package com.example.dailybriefing

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewModelScope
import com.example.dailybriefing.databinding.ActivityMainBinding
import com.example.dailybriefing.databinding.ClockViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ClockCustomView : ConstraintLayout {

    lateinit var binding: ClockViewBinding

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        if (attrs != null) {
            initAttrs(attrs)
            initView()
            clockLunch()
        }
    }

    /*
    var date: String
        get() {
            return textPaint.textSize
        }
        set(value) {
            textPaint.textSize = value
            invalidate()
        }

     */

    private fun initAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ClockCustomView,
            0, 0
        ).apply {
            // 속성으로 전달받은 값을 대입하는 부분
            try {
                //date = getString(R.styleable.ClockCustomView_date) ?: ""
            } finally {
                recycle()
            }
        }
    }

    private fun initView() {
        binding = ClockViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    private fun clockLunch() {

        var secView = true

        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                binding.date.text = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.M.d")))
                binding.dayOfWeek.text = (LocalDateTime.now().format(DateTimeFormatter.ofPattern(" E").withLocale(
                    Locale.forLanguageTag("en"))))

                binding.meridiem.text = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("a").withLocale(
                    Locale.forLanguageTag("en"))))

                 if(secView){
                     binding.time.text = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("h mm")))
                     secView=false
                }else{
                     binding.time.text = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm")))
                     secView=true
                }
                delay(1000)
            }
        }
    }

}