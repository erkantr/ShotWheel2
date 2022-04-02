package com.agency11.shotwheel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R

class TartismaZamaniFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialog_text = "Lütfen telefon herkesin görebileceği bir yerde dursun.\n" +
                "\n" +
                "Ekrana gelecek konu hakkında herkes savunmasını yapmalıdır çünkü kimin kazandığına herkes karar verecek.\n" +
                "\n" +
                "180 saniye süreniz var\n" +
                "\n" +
                "Bol şanslar..."
        val dialogs = Dialogs(requireContext())
        dialogs.getInfoDialog(dialog_text,"Soruyu Cevapla")

        return inflater.inflate(R.layout.fragment_tartisma_zamani, container, false)
    }
}