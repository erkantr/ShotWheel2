package com.agency11.shotwheel

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.agency11.shotwheel.databinding.ActivityMainBinding
import java.io.Serializable
import java.util.*

class MainActivity : AppCompatActivity() {

    private val sectors: Array<String> = arrayOf(
        "Ben Kimim?",
        "Taklit Et",
        "İtiraf Et",
        "Tartışma Zamanı",
        "Doğruluk Cesaret",
        "Soruyu Cevapla",
        "Dans Zamanı",
        "Çizerek Anlat"
    )
    private val sectorDegrees: Array<Int> = arrayOf(0,1, 2, 3, 4, 5, 6, 7, 8)
    private var degree: Int = 0
    private var isSpinning: Boolean = false
    private val random = Random()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        getDegreeForSectors()

        binding.roulette.setOnClickListener {
            if (!isSpinning) {
                spin()
                isSpinning = true
            }
        }


    }


    private fun spin() {
        degree = random.nextInt(sectors.size - 1)
        val rotateAnimation = RotateAnimation(
            0F,
            (((360 * sectors.size) + sectorDegrees[degree]).toFloat()),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5F,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )

        rotateAnimation.duration = 7000
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                if (sectors.size - (degree + 1) + 1 == 8) {
                    Toast.makeText(this@MainActivity, "position " + sectors[0], Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "position " + sectors[sectors.size - (degree + 1) + 1],
                        Toast.LENGTH_SHORT
                    ).show()
                }
                isSpinning = false
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })

        binding.roulette.startAnimation(rotateAnimation)

    }

    private fun getDegreeForSectors() {
        var sectorDegree = 360 / sectors.size

        for (a in sectors.indices) {
            sectorDegrees[a] = (a + 1) * sectorDegree
        }
    }
}
