package com.agency11.shotwheel.fragment

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.Dialogs
import com.agency11.shotwheel.R
import com.agency11.shotwheel.databinding.ActivityMainBinding
import com.agency11.shotwheel.databinding.FragmentBenKimimBinding
import com.agency11.shotwheel.databinding.FragmentDansZamaniBinding
import java.time.Duration
import kotlin.random.Random

class DansZamaniFragment : Fragment() {

    val musics: Array<Int> = arrayOf(R.raw.raggae,R.raw.rock,R.raw.romanticdance,R.raw.samba,R.raw.disko,R.raw.hiphop,
        R.raw.swing,R.raw.trap,R.raw.disko,R.raw.hiphop)
    lateinit var binding: FragmentDansZamaniBinding

    lateinit var runnable: Runnable
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDansZamaniBinding.inflate(inflater,container,false)
        val view = binding.root

        val dialog_text = "Önce seni bi ayağa alalım şanslı dostum.\n" +
                "\n" +
                "Senin için hazırladığımız müzik paketlerinden rastgele biri çalmaya başlamadan  oynamaya hazır olsan iyi edersin.\n" +
                "\n" +
                "Alacağın puan arkadaşlarının elinde ;)\n" +
                "\n" +
                "Bol şans..."
        val dialogs = Dialogs(requireContext())

        val random_music = Random.nextInt(musics.size -1)
        var mediaPlayer = MediaPlayer.create(requireContext(), musics[random_music])
        dialogs.getInfoDialog(dialog_text,"Dans Zamanı",mediaPlayer)

        val preferences = requireContext().getSharedPreferences("players", Context.MODE_PRIVATE)

        val p1point = preferences.getInt("p1point",0)
        val p2point = preferences.getInt("p2point",0)
        val p3point = preferences.getInt("p3point",0)
        val p4point = preferences.getInt("p4point",0)
        val p5point = preferences.getInt("p5point",0)
        val p6point = preferences.getInt("p6point",0)

        val list = arrayOf(p1point,p2point,p3point,p4point,p5point,p6point)

        binding.seekBar.progress = 0
        binding.seekBar.max = mediaPlayer.duration
        //mediaPlayer.start()

        //val bundle = arguments
        //val player = bundle!!.getString("player")!!

        val player = preferences.getString("currplayer","")!!

        runnable = Runnable {
            binding.seekBar.progress = mediaPlayer.currentPosition
            val pos = mediaPlayer.currentPosition
            val duration = mediaPlayer.duration
            duration(pos,duration,binding.duration)
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.pause()
            dialogs.getDansOylamasiDialog(player, requireActivity())
            for (a in list.indices){
                println(list[a])
            }
        }

        return view//inflater.inflate(R.layout.fragment_dans_zamani, container, false)
    }
    fun duration(pos: Int, duration: Int, tw: TextView){
        var duration_text = "0"
        val durationmin = duration / 1000 / 60
        val durationsec = duration / 1000 % 60

        var pos_text = "0"
        val posmin = pos / 1000 / 60
        val possec = pos / 1000 % 60

        pos_text = "0$posmin:"
        if (possec <10){
            pos_text +="0"
            pos_text += possec
        }else if(possec < 60) {
            pos_text += possec
        }

        duration_text = "0$durationmin:"
        if (durationsec < 10){
            duration_text +="0"
            duration_text += durationsec
        }else if(durationsec < 60) {
            duration_text += durationsec
        }
        tw.setText("$pos_text/$duration_text")
    }
}