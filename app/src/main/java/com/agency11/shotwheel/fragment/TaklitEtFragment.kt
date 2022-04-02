package com.agency11.shotwheel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R

class TaklitEtFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialog_text = "Lütfen telefonu eline al ve kimseye gösterme.\n" +
                "\n" +
                "Ekrana gelecek kelimeyi taklit ederek arkadaşlarına anlatmak için 30 saniye süren olacak.\n" +
                "\n" +
                "Hazırsan başlayalım.."
        val dialogs = Dialogs(requireContext())
        dialogs.getInfoDialog(dialog_text,"Soruyu Cevapla")

        return inflater.inflate(R.layout.fragment_taklit_et, container, false)
    }
}