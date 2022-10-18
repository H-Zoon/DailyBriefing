package com.example.dailybriefing

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.animation.AccelerateInterpolator
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintSet
import com.example.dailybriefing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var isopen = true
        val briefingFragment = BriefingFragment()



        binding.button.setOnClickListener {
            if (isopen) {
                updateView(R.layout.activity_main_frag)
                val transaction = supportFragmentManager.beginTransaction()
                    .replace(binding.briefingFrag.id, briefingFragment)
                transaction.commit()

            } else {
                updateView(R.layout.activity_main)

                val transaction = supportFragmentManager.beginTransaction()
                transaction.remove(supportFragmentManager.findFragmentById(binding.briefingFrag.id)!!)
                transaction.commit()
            }
            isopen = !isopen
        }


    }

    fun updateView(@LayoutRes id: Int) {
        val targetConstSet = ConstraintSet()
        targetConstSet.clone(this, id)
        targetConstSet.applyTo(binding.root)

        val trans = ChangeBounds()
        trans.interpolator = AccelerateInterpolator()
        TransitionManager.beginDelayedTransition(binding.root, trans)
    }

}

