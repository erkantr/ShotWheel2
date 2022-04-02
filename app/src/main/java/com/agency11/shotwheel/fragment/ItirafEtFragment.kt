package com.agency11.shotwheel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R

class ItirafEtFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialog_text = "Lütfen telefon herkesin görebileceği bir yerde dursun.\n" +
                "\n" +
                "Ekrana gelecek konu hakkında önce15 saniye düşünme süreniz olacak ardından  120 saniyelik süre başlayacak. En sağlam itirafı yapan oyunu alır ;)\n" +
                "\n" +
                "Let the game begin...\n"
        val dialogs = Dialogs(requireContext())
        dialogs.getInfoDialog(dialog_text,"İtiraf Et")

        return inflater.inflate(R.layout.fragment_itiraf_et, container, false)
    }
}