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
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.data.SoruyuCevapla
import com.agency11.shotwheel.databinding.FragmentSoruCevaplaBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.agency11.shotwheel.R
import com.agency11.shotwheel.activity.MainActivity
import com.agency11.shotwheel.activity.PrepareScreen
import kotlin.concurrent.thread

class SoruCevaplaFragment : Fragment() {

    private lateinit var database: DatabaseReference
    lateinit var timer: CountDownTimer

    //lateinit var binding: FragmentSoruCevaplaBinding

     var soru = ""
     var a = ""
     var b = ""
     var c = ""
     var d = ""
     lateinit var cevap: String
     var player = ""
    lateinit var soru_text: TextView
    lateinit var b_text: TextView
    lateinit var c_text: TextView
    lateinit var d_text: TextView
    lateinit var a_text: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding = FragmentSoruCevaplaBinding.inflate(inflater, container, false)
        //val view = binding.root
        val view = inflater.inflate(R.layout.fragment_soru_cevapla, container, false)

        val dialog_text = "Ekrana gelecek soruyu cevaplamak için 30 saniye süren olacak.\n" +
                "\n" +
                "Kendini hazır hissettiğin an başlayabilirsin."
        val dialog_text2 = "Alooooo, süre bitti aloooooooo.\n" +
                "\n" +
                "Çekmiyor mu?\n"
        val dialogs = Dialogs(requireContext())

        val background: RelativeLayout = view.findViewById(R.id.background)

        getSoruCevapla()

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)
        player = preferences.getString("currplayer", "")!!

        val linearLayout1: LinearLayout = view.findViewById(R.id.question_layout)
        soru_text = view.findViewById(R.id.soru)
        a_text = view.findViewById(R.id.a)
        b_text = view.findViewById(R.id.b)
        c_text = view.findViewById(R.id.c)
        d_text = view.findViewById(R.id.d)

        for (i in 0..3) {
            linearLayout1.getChildAt(i).setOnClickListener { v -> checkAnswer(v as Button) }
        }

        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                var time: TextView = view.findViewById(R.id.time)
                val timetext = p0 / 1000 % 60
                time.text = "$timetext"
            }


            override fun onFinish() {
                //val activity: Activity = requireActivity()
                if(activity != null && isAdded){
                    dialogs.getEndDialog(dialog_text2, "Devam", activity!!)
                }
                if(context!=null && isAdded){
                    val mediaPlayer = MediaPlayer.create(context, R.raw.sad_trombone)
                    mediaPlayer.start()
                }

            }

        }
        dialogs.getInfoDialog(background,dialog_text, "Soruyu Cevapla", null, timer)

        return view
    }
    fun getSoruCevapla() {
        database = FirebaseDatabase.getInstance().reference.child("soruyu_cevapla")
            .child((1..6).random().toString())

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.getValue<SoruyuCevapla>()!!

                soru = post.soru.toString()
                a = post.a.toString()
                b = post.b.toString()
                c = post.c.toString()
                d = post.d.toString()
                cevap = post.dogru_cevap.toString()

                soru_text.text = soru
                a_text.text = a
                b_text.text = b
                c_text.text = c
                d_text.text = d

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    fun checkAnswer(selectedOptions: Button) {

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val post = snapshot.getValue<SoruyuCevapla>()!!

                soru = post.soru.toString()
                a = post.a.toString()
                b = post.b.toString()
                c = post.c.toString()
                d = post.d.toString()
                cevap = post.dogru_cevap.toString()

                if (selectedOptions.text.toString() == cevap) {
                    selectedOptions.setBackgroundResource(R.drawable.question_true)
                    val dialogs = Dialogs(requireContext())
                    dialogs.addPoint(player,15)
                    Toast.makeText(requireContext(),"Hadi yine iyisin, kaptın puanı", Toast.LENGTH_SHORT).show()
                    timer.cancel()
                    thread(start = true){
                        try {
                            synchronized(this){
                                Thread.sleep(2000)
                            }
                        } catch (e : InterruptedException){
                            e.printStackTrace()
                        } finally {
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }

                    }

                } else {
                    when(cevap){
                        a ->{
                            a_text.setBackgroundResource(R.drawable.question_true)
                        }
                        b ->{
                            b_text.setBackgroundResource(R.drawable.question_true)
                        }
                        c ->{
                            c_text.setBackgroundResource(R.drawable.question_true)
                        }
                        d ->{
                            d_text.setBackgroundResource(R.drawable.question_true)
                        }
                    }
                    //correctOption.setBackgroundResource(R.drawable.question_true)
                    //Toast.makeText(requireContext(),cevap, Toast.LENGTH_SHORT).show()

                    selectedOptions.setBackgroundResource(R.drawable.question_false)
                    Toast.makeText(requireContext(),"Sorun değil, karpuz da yata yata büyür", Toast.LENGTH_SHORT).show()
                    thread(start = true){
                        try {
                            synchronized(this){
                                Thread.sleep(1000)
                            }
                        } catch (e : InterruptedException){
                            e.printStackTrace()
                        } finally {
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }

                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                println(error)
            }

        })
    }

}
