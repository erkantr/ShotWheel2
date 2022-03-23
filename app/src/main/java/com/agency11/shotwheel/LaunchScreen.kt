package com.agency11.shotwheel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agency11.shotwheel.databinding.ActivityLaunchScreenBinding

class LaunchScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)









    }
}