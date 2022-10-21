package com.example.dailybriefing

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.animation.AccelerateInterpolator
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.dailybriefing.databinding.ActivityMainBinding
import com.example.dailybriefing.fragment.BriefingFragment

class MainActivity : AppCompatActivity() {

    companion object {
        val prefs: SharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(App.context())
    }

    lateinit var binding: ActivityMainBinding

    @SuppressLint("ObjectAnimatorBinding")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var briefingIsOpen = false
        val briefingFragment = BriefingFragment()

        when (prefs.getString("theme", "auto")) {
            "light" -> {
                binding.setting.background =
                    ContextCompat.getDrawable(this, R.drawable.outline_light_mode_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            "dark" -> {
                binding.setting.background =
                    ContextCompat.getDrawable(this, R.drawable.outline_dark_mode_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            "auto" -> {
                binding.setting.background =
                    ContextCompat.getDrawable(this, R.drawable.outline_hdr_auto_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        binding.briefing.setOnClickListener {
            if (!briefingIsOpen) {
                val transaction = supportFragmentManager.beginTransaction()
                    .replace(binding.briefingFrag.id, briefingFragment)
                transaction.commit()
                updateView(R.layout.activity_main_frag)

            } else {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.remove(supportFragmentManager.findFragmentById(binding.briefingFrag.id)!!)
                transaction.commit()
                updateView(R.layout.activity_main)
            }
            briefingIsOpen = !briefingIsOpen
        }

        binding.setting.setOnClickListener {
            nightModeChanger()
        }
    }

    private fun updateView(@LayoutRes id: Int) {
        val targetConstSet = ConstraintSet()
        targetConstSet.clone(this, id)
        targetConstSet.applyTo(binding.root)

        val trans = ChangeBounds()
        trans.interpolator = AccelerateInterpolator()
        TransitionManager.beginDelayedTransition(binding.root, trans)
    }

    private fun nightModeChanger() {
        when (prefs.getString("theme", "auto")) {
            "light" -> {
                binding.setting.background =
                    ContextCompat.getDrawable(this, R.drawable.outline_dark_mode_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                with(prefs.edit()) {
                    putString("theme", "dark")
                }.apply()

            }

            "dark" -> {
                binding.setting.background =
                    ContextCompat.getDrawable(this, R.drawable.outline_hdr_auto_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                with(prefs.edit()) {
                    putString("theme", "auto")
                }.apply()
            }

            "auto" -> {
                binding.setting.background =
                    ContextCompat.getDrawable(this, R.drawable.outline_light_mode_24)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                with(prefs.edit()) {
                    putString("theme", "light")
                }.apply()

            }
        }
    }
}

