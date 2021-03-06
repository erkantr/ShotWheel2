package com.agency11.shotwheel.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
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
import com.agency11.shotwheel.Size
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

        val size = Size(requireContext())

        size.setMargin(binding.dogrulukCesaretLinear,0,45,0,0)
        size.setMargin(binding.dogrulukCesaretTextView,16,0,0,0)
        size.setSize(binding.dogrulukCesaretTextView,14)
        size.setMargin(binding.puan,12,0,0,0)
        size.setSize(binding.puan,20)
        size.setMargin(binding.dogrulukCesaretTextView2,33,0,0,0)
        size.setSize(binding.dogrulukCesaretTextView2,14)
        size.setMargin(binding.time,12,0,16,0)
        size.setSize(binding.time,20)
        size.setMargin(binding.kisi,0,238,0,0)
        size.setSize(binding.kisi,32)
        size.setWidth(binding.button,193)
        size.setHeight(binding.button,44)
        size.setMargin(binding.button,0,215,0,12)
        size.setSize(binding.button,16)


        val dialog_text = "Ger??ek oyundan tek fark??, soracak ki??iyi ben se??iyorum :P\n" +
                "\n" +
                "Haz??rsan, butona bas ve kimin soraca????n?? bekle..\n" +
                "\n" +
                "Sonras?? sende, keyfini ????kar ;)\n" +
                "\n" +
                "Yaln??z s??re bitmeden i??lemi tamamla butonuna basamazsan puan alamazs??n.\n" +
                "\n" +
                "Ve l??tfen zevkini ????karmadan butona basma.\n"
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
                val timetext = p0 / 1000
                time.text = "$timetext"
            }

            override fun onFinish() {
                Toast.makeText(context, "Sorun de??il, karpuz da yata yata b??y??r", Toast.LENGTH_LONG).show()
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
                val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sad_trombone)
                mediaPlayer.start()
            }

        }
                dialogs.getInfoDialog(binding.background,dialog_text, "Do??ruluk mu Cesaretlik mi", null, timer)

        binding.button.setOnClickListener {
            timer.cancel()
            dialogs.addPoint(player,23)
            Toast.makeText(context, "Hadi yine iyisin, kapt??n puan??", Toast.LENGTH_LONG).show()
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