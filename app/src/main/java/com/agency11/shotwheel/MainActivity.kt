package com.agency11.shotwheel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  

        println("inş")
        Toast.makeText(applicationContext,"deneme",Toast.LENGTH_LONG)
    }
}