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
import com.agency11.shotwheel.data.ItirafEt
import com.agency11.shotwheel.databinding.FragmentCizerekAnlatBinding
import com.agency11.shotwheel.databinding.FragmentItirafEtBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlin.concurrent.thread

class ItirafEtFragment : Fragment() {

    private lateinit var database: DatabaseReference
    lateinit var timer: CountDownTimer
    lateinit var timer2: CountDownTimer

    lateinit var binding: FragmentItirafEtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItirafEtBinding.inflate(inflater, container, false)
        val view = binding.root

        val dialog_text = "Lütfen telefon herkesin görebileceği bir yerde dursun.\n" +
                "\n" +
                "Ekrana gelecek konu hakkında önce15 saniye düşünme süreniz olacak ardından  150 saniyelik süre başlayacak. En sağlam itirafı yapan oyunu alır ;)\n" +
                "\n" +
                "Let the game begin...\n"
        val dialogs = Dialogs(requireContext())
        getItirafEt(binding.kelime)

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)
        val player = preferences.getString("currplayer", "")!!

        val time: TextView = binding.timer2

        timer = object : CountDownTimer(15000, 1000) {
            override fun onTick(p0: Long) {
                val timetext = p0 / 1000 % 60
                time.text = "Başlamaya son $timetext saniye"
            }

            override fun onFinish() {
                time.text = ""
                timer2 = object : CountDownTimer(150000, 1000) {
                    override fun onTick(p0: Long) {
                        val time: TextView = binding.time
                        val timetext = p0 / 1000
                        time.text = "$timetext"
                    }

                    override fun onFinish() {
                        //dialogs.getEndDialog(dialog_text, "Devam", requireActivity())
                        val activity: Activity = requireActivity()
                        if(activity != null && isAdded){
                            dialogs.getOylamaDialog(player,"Sizce kim en sağlam itirafı yaptı?",requireActivity(),30)
                        }
                    }

                }
                timer2.start()
            }

        }
                dialogs.getInfoDialog(dialog_text, "İtiraf Et", null, timer)

        /*
        binding.button.setOnClickListener {
            timer.cancel()
            dialogs.addPoint(player)
            Toast.makeText(context, "Hadi yine iyisin, kaptın puanı", Toast.LENGTH_LONG).show()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

         */

        return view
    }

    fun getItirafEt(tw1: TextView) {
        database = FirebaseDatabase.getInstance().reference.child("itiraf_et")
            .child((1..5).random().toString())

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.getValue<ItirafEt>()!!

                tw1.text = post.konu
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}