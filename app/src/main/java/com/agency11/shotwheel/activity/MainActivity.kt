package com.agency11.shotwheel.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R
import com.agency11.shotwheel.data.BenKimim
import com.agency11.shotwheel.databinding.ActivityMainBinding
import com.agency11.shotwheel.fragment.*
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.mongodb.Mongo
import com.mongodb.MongoClient
import java.util.*
import kotlin.concurrent.thread

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
    private val fragments: Array<Fragment> = arrayOf(
        BenKimimFragment(),
        TaklitEtFragment(),
        ItirafEtFragment(),
        TartismaZamaniFragment(),
        DogrulukCesaretlikFragment(),
        SoruCevaplaFragment(),
        DansZamaniFragment(),
        CizerekAnlatFragment()
    )

    private lateinit var database: DatabaseReference
    private lateinit var preferences: SharedPreferences

    var a = 0
    private val sectorDegrees: Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)
    private var degree: Int = 0
    private var isSpinning: Boolean = false
    private val random = Random()
    private lateinit var binding: ActivityMainBinding
    lateinit var playerList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        preferences = getSharedPreferences("players", Context.MODE_PRIVATE)

        val player1 = preferences.getString("player1","")!!
        val player2 = preferences.getString("player2","")!!
        val player3 = preferences.getString("player3","")!!
        val player4 = preferences.getString("player4","")!!
        val player5 = preferences.getString("player5","")!!
        val player6 = preferences.getString("player6","")!!
        val players = preferences.getInt("players", 3)

        val p1point = preferences.getInt("p1point",0)
        val p2point = preferences.getInt("p2point",0)
        val p3point = preferences.getInt("p3point",0)
        val p4point = preferences.getInt("p4point",0)
        val p5point = preferences.getInt("p5point",0)
        val p6point = preferences.getInt("p6point",0)

        val text = "Öncelikle hoşgeldiniz.\n" +
                "\n" +
                "Her kategorinin bilgilendirme penceresi, sırası gelen kişiyi yönlendirecektir.\n" +
                "\n" +
                "Çarkı döndürmek için üzerine bir kere dokunmak yeterlidir.\n" +
                "\n" +
                "Her kategori süreli ve puanlıdır.\n" +
                "\n" +
                "Sırasını geçmek isteyen pas butonunu kullanabilir.\n" +
                "\n" +
                "Ayrıca puan tablosundan puanlar görüntülenebilir.\n" +
                "\n" +
                "İyi eğlenceler..."

        val dialogs = Dialogs(this)
        if(preferences.getInt("game", 0) == 0){
                    dialogs.getMainInfoDialog()
        }
        val editor = preferences.edit()
        editor.putInt("game",1)
        editor.apply()

        dialogs.getPlayers(binding.currplayer)

        val playerList: Array<String> = arrayOf(player1,player2,player3,player4,player5,player6)

        binding.players.setOnClickListener {
            dialogs.getLeaderBoardDialog()
        }

        //binding.shotwheelScreen.visibility = View.VISIBLE
        //binding.frame.visibility = View.GONE

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

        rotateAnimation.duration = 15000
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = OvershootInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val intent = Intent(this@MainActivity,FragmentActivity::class.java)
                if (sectors.size - (degree + 1) + 1 == 8) {

                    intent.putExtra("fragment",sectors[0])

                    //val bundle = Bundle()
                    //bundle.putString("player", playerList[a])
                    //fragments[0].arguments = bundle
                    //changeFragment(fragments[0])
                    //fragments[6].arguments = bundle
                    //changeFragment(fragments[6])

                    //binding.shotwheelScreen.visibility = View.GONE
                    //binding.frame.visibility = View.VISIBLE

                } else if(sectors.size - (degree + 1) + 1 == 1){

                    intent.putExtra("fragment",sectors[8])

                    //val bundle = Bundle()
                    //bundle.putString("player", playerList[a])
                    //fragments[8].arguments = bundle
                    //fragments[6].arguments = bundle

                    //changeFragment(fragments[6])

                    //changeFragment(fragments[8])
                    //binding.shotwheelScreen.visibility = View.GONE
                    //binding.frame.visibility = View.VISIBLE


                } else {

                    intent.putExtra("fragment",sectors[sectors.size - (degree +1) +1])

                    //val bundle = Bundle()
                    //bundle.putString("player", playerList[a])
                    //fragments[sectors.size - (degree +1) +1].arguments = bundle
                    //fragments[6].arguments = bundle
                    //changeFragment(fragments[6])

                    //changeFragment(fragments[sectors.size - (degree +1) +1])
                    //binding.shotwheelScreen.visibility = View.GONE
                    //binding.frame.visibility = View.VISIBLE
                }
                isSpinning = false
                startActivity(intent)
                finish()
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

    fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }

    var backbtn = 0

    override fun onBackPressed() {
        backbtn += 1
        if (backbtn == 2){
            finish()
        } else{
            Toast.makeText(this,"Oyundan çıkmak için bir daha tıklayın",Toast.LENGTH_LONG).show()
        }
    }

}
