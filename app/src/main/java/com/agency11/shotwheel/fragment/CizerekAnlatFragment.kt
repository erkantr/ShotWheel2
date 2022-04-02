package com.agency11.shotwheel.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R
import com.agency11.shotwheel.data.BenKimim
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class CizerekAnlatFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialog_text = "Lütfen telefonu eline al ve ekranı kimseye gösterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi, yanında hazır bulundurduğun kağıda çizerek anlatmak için 30 saniye süren var.\n" +
                "\n" +
                "Umarım kelimeyi kağıda yazacak kadar zeki değilsindir.\n" +
                "\n" +
                "Her şey hazırsa başlayalım...\n"
        val dialogs = Dialogs(requireContext())
        dialogs.getInfoDialog(dialog_text,"Çizerek Anlat")

        return inflater.inflate(R.layout.fragment_cizerek_anlat, container, false)
    }
}