package com.agency11.shotwheel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R

class DogrulukCesaretlikFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialog_text = "Gerçek oyundan tek farkı, soracağın kişiyi ben seçiyorum :P\n" +
                "\n" +
                "Hazırsan, butona bas ve sistemin hangi kurbanı seçeceğini bekle..\n" +
                "\n" +
                "Sonrası sende, keyfini çıkar ;)\n" +
                "\n" +
                "Yalnız süre bitmeden işlemi tamamla butonuna basamazsan puan alamazsın.\n" +
                "\n" +
                "Ve lütfen zevkini çıkarmadan butona basma."
        val dialogs = Dialogs(requireContext())
        dialogs.getInfoDialog(dialog_text,"Doğruluk mu Cesaretlik mi")

        return inflater.inflate(R.layout.fragment_dogruluk_cesaretlik, container, false)
    }
}