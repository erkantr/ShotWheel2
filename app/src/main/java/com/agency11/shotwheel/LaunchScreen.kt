package com.agency11.shotwheel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agency11.shotwheel.databinding.ActivityLaunchScreenBinding
import java.lang.Exception
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class LaunchScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchScreenBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        thread(start = true){

            try {
                synchronized(this){
                    Thread.sleep(3000)
                }
                } catch (e : InterruptedException){
                    e.printStackTrace()
                } finally {
                    //val intent = Intent(this@LaunchScreen, PrepareScreen::class.java)
                    startActivity(Intent(this@LaunchScreen, PrepareScreen::class.java))
                }

            }

        }

    }
