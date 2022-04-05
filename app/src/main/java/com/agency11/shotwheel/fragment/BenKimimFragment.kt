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

        val dialog_text = "Lütfen telefonu eline al ve ekranı kimseye gösterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi, altındaki yasaklı kelimeleri kullanmadan anlatmak için 30 saniye süren var.\n" +
                "\n" +
                "Hazırsan başlayalım...\n"

        val dialog_text2 = "Beceriksiz.\n" +
                "\n" +
                "Hayır 29.5 saniye verecektim, son anda kararımı değiştirip 30 saniye yaptım.\n" +
                "\n" +
                "Bırak şu telefonu elinden.\n"

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