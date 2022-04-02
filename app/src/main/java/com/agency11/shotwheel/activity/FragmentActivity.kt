package com.agency11.shotwheel.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.R
import com.agency11.shotwheel.databinding.ActivityFragmentBinding
import com.agency11.shotwheel.fragment.*

class FragmentActivity : AppCompatActivity() {

    lateinit var binding: ActivityFragmentBinding

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

    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        preferences = getSharedPreferences("players", Context.MODE_PRIVATE)

        val intent = getIntent()
        val fragment = intent.getStringExtra("fragment")

        when(fragment){
            "Ben Kimim?"-> changeFragment(fragments[0])
            "Taklit Et"-> changeFragment(fragments[1])
            "İtiraf Et"-> changeFragment(fragments[2])
            "Tartışma Zamanı"-> changeFragment(fragments[3])
            "Doğruluk Cesaretlik"-> changeFragment(fragments[4])
            "Soruyu Cevapla"-> changeFragment(fragments[5])
            "Dans Zamanı"-> changeFragment(fragments[6])
            "Çizerek Anlat"-> changeFragment(fragments[7])
        }

    }

    fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }
}