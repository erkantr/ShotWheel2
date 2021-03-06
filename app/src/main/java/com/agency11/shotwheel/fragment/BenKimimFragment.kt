package com.agency11.shotwheel.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Range
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R
import com.agency11.shotwheel.Size
import com.agency11.shotwheel.activity.MainActivity
import com.agency11.shotwheel.data.BenKimim
import com.agency11.shotwheel.databinding.FragmentBenKimimBinding
import com.agency11.shotwheel.databinding.FragmentDansZamaniBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.RandomAccess
import kotlin.concurrent.thread

class BenKimimFragment : Fragment() {

    private lateinit var database: DatabaseReference
    lateinit var timer: CountDownTimer

    lateinit var binding: FragmentBenKimimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBenKimimBinding.inflate(inflater, container, false)
        val view = binding.root

        val size = Size(requireContext())
        size.setMargin(binding.whoamILinear,0,45,0,0)
        size.setMargin(binding.whoamITextView,16,0,0,0)
        size.setSize(binding.whoamITextView,14)
        size.setMargin(binding.puan,12,0,0,0)
        size.setSize(binding.puan,20)
        size.setMargin(binding.whoamITextView2,33,0,0,0)
        size.setSize(binding.whoamITextView2,14)
        size.setMargin(binding.time,12,0,16,0)
        size.setSize(binding.time,20)
        size.setMargin(binding.kelime,0,143,0,0)
        size.setSize(binding.kelime,32)
        size.setMargin(binding.yasakliKelimeler,0,16,0,0)
        size.setSize(binding.yasakliKelimeler,24)
        size.setWidth(binding.button,122)
        size.setHeight(binding.button,44)
        size.setMargin(binding.button,0,120,0,12)
        size.setSize(binding.button,16)

        val dialog_text = "L??tfen telefonu eline al ve ekran?? kimseye g??sterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi, alt??ndaki yasakl?? kelimeleri kullanmadan anlatmak i??in 30 saniye s??ren var.\n" +
                "\n" +
                "Haz??rsan ba??layal??m...\n"

        val dialog_text2 = "Beceriksiz.\n" +
                "\n" +
                "Hay??r 29.5 saniye verecektim, son anda karar??m?? de??i??tirip 30 saniye yapt??m.\n" +
                "\n" +
                "B??rak ??u telefonu elinden.\n"

        val dialogs = Dialogs(requireContext())

        getBenKimim(binding.kelime,binding.yasakliKelimeler)

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)
        val player = preferences.getString("currplayer", "")!!

        timer = object : CountDownTimer(30000, 1000) {
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
                dialogs.getInfoDialog(binding.background,dialog_text, "Ben Kimim", null, timer)

        binding.button.setOnClickListener {
            timer.cancel()
            dialogs.addPoint(player,10)
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

    fun getBenKimim(tw1: TextView, tw2: TextView) {
        database = FirebaseDatabase.getInstance().reference.child("ben_kimim")
            .child((1..6).random().toString())

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.getValue<BenKimim>()!!

                tw1.text = post.kelime
                tw2.text = post.yasakli_kelimeler
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}