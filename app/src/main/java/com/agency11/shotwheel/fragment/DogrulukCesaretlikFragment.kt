package com.agency11.shotwheel.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R
import com.agency11.shotwheel.activity.MainActivity
import com.agency11.shotwheel.data.CizerekAnlat
import com.agency11.shotwheel.databinding.FragmentCizerekAnlatBinding
import com.agency11.shotwheel.databinding.FragmentDogrulukCesaretlikBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlin.concurrent.thread

class DogrulukCesaretlikFragment : Fragment() {
    private lateinit var database: DatabaseReference
    lateinit var timer: CountDownTimer

    lateinit var binding: FragmentDogrulukCesaretlikBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogrulukCesaretlikBinding.inflate(inflater, container, false)
        val view = binding.root

        val dialog_text = "Gerçek oyundan tek farkı, soracak kişiyi ben seçiyorum :P\n" +
                "\n" +
                "Hazırsan, butona bas ve kimin soracağını bekle..\n" +
                "\n" +
                "Sonrası sende, keyfini çıkar ;)\n" +
                "\n" +
                "Yalnız süre bitmeden işlemi tamamla butonuna basamazsan puan alamazsın.\n" +
                "\n" +
                "Ve lütfen zevkini çıkarmadan butona basma.\n"
        val dialogs = Dialogs(requireContext())

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)
        val player = preferences.getString("currplayer", "")!!

        val player1_text = preferences.getString("player1", "")!!
        val player2_text = preferences.getString("player2", "")!!
        val player3_text = preferences.getString("player3", "")!!
        val player4_text = preferences.getString("player4", null)!!
        val player5_text = preferences.getString("player5", null)!!
        val player6_text = preferences.getString("player6", null)!!
        val players = preferences.getInt("players", 3)
        val playerList: Array<String> = arrayOf(
            player1_text,
            player2_text,
            player3_text,
            player4_text,
            player5_text,
            player6_text
        )

        val b = players -1

        for (a in 0..b){
            if(playerList[a] != player){
            binding.kisi.text = playerList[a]
            }
        }

        timer = object : CountDownTimer(75000, 1000) {
            override fun onTick(p0: Long) {
                var time: TextView = binding.time
                val timetext = p0 / 1000 % 60
                time.text = "$timetext"
            }

            override fun onFinish() {
                Toast.makeText(context, "Sorun değil, karpuz da yata yata büyür", Toast.LENGTH_LONG).show()
                thread(start = true){
                    try {
                        synchronized(this){
                            Thread.sleep(1000)
                        }
                    } catch (e : InterruptedException){
                        e.printStackTrace()
                    } finally {
                        startActivity(Intent(requireActivity(),MainActivity::class.java))
                        requireActivity().finish()
                    }
                }
            }

        }
                dialogs.getInfoDialog(dialog_text, "Doğruluk mu Cesaretlik mi", null, timer)

        binding.button.setOnClickListener {
            timer.cancel()
            dialogs.addPoint(player,23)
            Toast.makeText(context, "Hadi yine iyisin, kaptın puanı", Toast.LENGTH_LONG).show()
            thread(start = true){
                try {
                    synchronized(this){
                        Thread.sleep(1000)
                    }
                } catch (e : InterruptedException){
                    e.printStackTrace()
                } finally {
                    startActivity(Intent(requireActivity(),MainActivity::class.java))
                    requireActivity().finish()
                }

            }
        }

        return view
    }
}