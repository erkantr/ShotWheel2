package com.agency11.shotwheel.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R
import com.agency11.shotwheel.activity.MainActivity
import com.agency11.shotwheel.data.BenKimim
import com.agency11.shotwheel.data.CizerekAnlat
import com.agency11.shotwheel.databinding.FragmentBenKimimBinding
import com.agency11.shotwheel.databinding.FragmentCizerekAnlatBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlin.concurrent.thread

class CizerekAnlatFragment : Fragment() {

    private lateinit var database: DatabaseReference
    lateinit var timer: CountDownTimer

    lateinit var binding: FragmentCizerekAnlatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCizerekAnlatBinding.inflate(inflater, container, false)
        val view = binding.root

        val dialog_text = "Lütfen telefonu eline al ve ekranı kimseye gösterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi, yanında hazır bulundurduğun kağıda çizerek anlatmak için 45 saniye süren var.\n" +
                "\n" +
                "Umarım kelimeyi kağıda yazacak kadar zeki değilsindir.\n" +
                "\n" +
                "Her şey hazırsa başlayalım...\n"
        val dialog_text2 = "Lütfen o kağıdı daha fazla ziyan etme!\n" +
                "\n" +
                "Belli ki 30 dakika bile versem çizemeyeceksin."
        val dialogs = Dialogs(requireContext())
        getCizerekAnlat(binding.kelime)

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)
        val player = preferences.getString("currplayer", "")!!


        timer = object : CountDownTimer(45000, 1000) {
            override fun onTick(p0: Long) {
                var time: TextView = binding.time
                val timetext = p0 / 1000 % 60
                time.text = "$timetext"
            }

            override fun onFinish() {
                val activity: Activity = requireActivity()
                if(activity != null && isAdded){
                    dialogs.getEndDialog(dialog_text2, "Devam", activity)
                }
            }

        }
                dialogs.getInfoDialog(dialog_text, "Çizerek Anlat", null, timer)

        binding.button.setOnClickListener {
            timer.cancel()
            dialogs.addPoint(player,15)
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
                }

            }
        }

        return view
    }

    fun getCizerekAnlat(tw1: TextView) {
        database = FirebaseDatabase.getInstance().reference.child("yasakli_kelimeler")
            .child((1..6).random().toString())

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.getValue<CizerekAnlat>()!!

                tw1.text = post.konu
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}