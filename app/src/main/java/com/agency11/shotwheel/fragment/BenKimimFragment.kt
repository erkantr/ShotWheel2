package com.agency11.shotwheel.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R
import com.agency11.shotwheel.data.BenKimim
import com.agency11.shotwheel.databinding.FragmentBenKimimBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*

class BenKimimFragment : Fragment() {

    private lateinit var database: DatabaseReference

   // lateinit var binding: FragmentBenKimimBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialog_text = "Lütfen telefonu eline al ve ekranı kimseye gösterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi, altındaki yasaklı kelimeleri kullanmadan anlatmak için 30 saniye süren var.\n" +
                "\n" +
                "Hazırsan başlayalım...\n"
        val dialogs = Dialogs(requireContext())
        dialogs.getInfoDialog(dialog_text,"Ben Kimim")

        val timer = object: CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                var time:TextView = view!!.findViewById(R.id.time)
                val timetext = p0 / 1000 % 60
                time.text = "$timetext"
            }

            override fun onFinish() {
                dialogs.getInfoDialog(dialog_text,"Süre Doldu")
            }

        }
        timer.start()

        return inflater.inflate(R.layout.fragment_ben_kimim, container, false)
    }
}