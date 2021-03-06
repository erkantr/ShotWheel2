package com.agency11.shotwheel.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
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
import com.agency11.shotwheel.Size
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

        val size = Size(requireContext())

        size.setMargin(binding.cizerekAnlatLinear,0,45,0,0)
        size.setMargin(binding.cizerekAnlatTextView,16,0,0,0)
        size.setSize(binding.cizerekAnlatTextView,14)
        size.setMargin(binding.puan,12,0,0,0)
        size.setSize(binding.puan,20)
        size.setMargin(binding.cizerekAnlatTextView2,33,0,0,0)
        size.setSize(binding.cizerekAnlatTextView2,14)
        size.setMargin(binding.time,12,0,16,0)
        size.setSize(binding.time,20)
        size.setMargin(binding.kelime,0,238,0,0)
        size.setSize(binding.kelime,32)
        size.setWidth(binding.button,122)
        size.setHeight(binding.button,44)
        size.setMargin(binding.button,0,120,0,12)
        size.setSize(binding.button,16)




        val dialog_text = "L??tfen telefonu eline al ve ekran?? kimseye g??sterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi, yan??nda haz??r bulundurdu??un ka????da ??izerek anlatmak i??in 45 saniye s??ren var.\n" +
                "\n" +
                "Umar??m kelimeyi ka????da yazacak kadar zeki de??ilsindir.\n" +
                "\n" +
                "Her ??ey haz??rsa ba??layal??m...\n"
        val dialog_text2 = "L??tfen o ka????d?? daha fazla ziyan etme!\n" +
                "\n" +
                "Belli ki 30 dakika bile versem ??izemeyeceksin.\n"
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
                val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sad_trombone)
                mediaPlayer.start()
            }

        }
                dialogs.getInfoDialog(binding.background,dialog_text, "??izerek Anlat", null, timer)

        binding.button.setOnClickListener {
            timer.cancel()
            dialogs.addPoint(player,15)
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