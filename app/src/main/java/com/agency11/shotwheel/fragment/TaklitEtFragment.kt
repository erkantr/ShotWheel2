package com.agency11.shotwheel.fragment

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
import com.agency11.shotwheel.data.TaklitEt
import com.agency11.shotwheel.databinding.FragmentCizerekAnlatBinding
import com.agency11.shotwheel.databinding.FragmentTaklitEtBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlin.concurrent.thread

class TaklitEtFragment : Fragment() {
    private lateinit var database: DatabaseReference
    lateinit var timer: CountDownTimer

    lateinit var binding: FragmentTaklitEtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaklitEtBinding.inflate(inflater, container, false)
        val view = binding.root

        val dialog_text = "Lütfen telefonu eline al ve kimseye gösterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi taklit ederek arkadaşlarına anlatmak için 20 saniye süren olacak.\n" +
                "\n" +
                "Hazırsan başlayalım.."
        val dialogs = Dialogs(requireContext())
        getTaklitEt(binding.kelime)

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)
        val player = preferences.getString("currplayer", "")!!


        timer = object : CountDownTimer(20000, 1000) {
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
                dialogs.getInfoDialog(dialog_text, "Taklit Et", null, timer)

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
                }

            }
        }

        return view
    }
    fun getTaklitEt(tw1: TextView) {
        database = FirebaseDatabase.getInstance().reference.child("taklit_et")
            .child((1..5).random().toString())

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.getValue<TaklitEt>()!!

                tw1.text = post.karakter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}