package com.agency11.shotwheel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R

class SoruCevaplaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialog_text = "Ekrana gelecek soruyu cevaplamak için 30 saniye süren olacak.\n" +
                "\n" +
                "Kendini hazır hissettiğin an başlayabilirsin."
        val dialogs = Dialogs(requireContext())
        dialogs.getInfoDialog(dialog_text,"Soruyu Cevapla")

        return inflater.inflate(R.layout.fragment_soru_cevapla, container, false)
    }
}