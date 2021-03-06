package com.agency11.shotwheel.fragment

import android.app.Activity
import android.content.Context
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
import com.agency11.shotwheel.data.CizerekAnlat
import com.agency11.shotwheel.data.TartismaZamani
import com.agency11.shotwheel.databinding.FragmentCizerekAnlatBinding
import com.agency11.shotwheel.databinding.FragmentTartismaZamaniBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlin.concurrent.thread

class TartismaZamaniFragment : Fragment() {
    private lateinit var database: DatabaseReference
    lateinit var timer: CountDownTimer

    lateinit var binding: FragmentTartismaZamaniBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTartismaZamaniBinding.inflate(inflater, container, false)
        val view = binding.root

        val size = Size(requireContext())

        size.setMargin(binding.tartismaZamaniLinear,0,45,0,0)
        size.setMargin(binding.tartismaZamaniTextView,16,0,0,0)
        size.setSize(binding.tartismaZamaniTextView,14)
        size.setMargin(binding.puan,12,0,0,0)
        size.setSize(binding.puan,20)
        size.setMargin(binding.tartismaZamaniTextView2,33,0,0,0)
        size.setSize(binding.tartismaZamaniTextView2,14)
        size.setMargin(binding.time,12,0,16,0)
        size.setSize(binding.time,20)
        size.setMargin(binding.kelime,5,238,5,0)
        size.setSize(binding.kelime,32)



        val dialog_text = "Lütfen telefon herkesin görebileceği bir yerde dursun.\n" +
                "\n" +
                "Ekrana gelecek konu hakkında herkes savunmasını yapmalıdır çünkü kimin kazandığına herkes karar verecek.\n" +
                "\n" +
                "180 saniye süreniz var\n" +
                "\n" +
                "Bol şanslar...\n"
        val dialogs = Dialogs(requireContext())
        getTartismaZamani(binding.kelime)

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)
        val player = preferences.getString("currplayer", "")!!


        timer = object : CountDownTimer(180000, 1000) {
            override fun onTick(p0: Long) {
                var time: TextView = binding.time
                val timetext = p0 / 1000
                time.text = "$timetext"
            }

            override fun onFinish() {
                timer.cancel()
                val activity: Activity = requireActivity()
                if(activity != null && isAdded){
                    dialogs.getOylamaDialog(player, "Sizce en sağlam tartışmacı kim?", requireActivity(),30)
                }
                val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.sad_trombone)
                mediaPlayer.start()
            }

        }
                dialogs.getInfoDialog(binding.background,dialog_text, "Tartışma Zamanı", null, timer)


        return view
    }
    fun getTartismaZamani(tw1: TextView) {
        database = FirebaseDatabase.getInstance().reference.child("tartisma_zamani")
            .child((1..5).random().toString())

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.getValue<TartismaZamani>()!!

                tw1.text = post.konu
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}