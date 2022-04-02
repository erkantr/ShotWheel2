package com.agency11.shotwheel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.agency11.shotwheel.activity.MainActivity
import com.agency11.shotwheel.fragment.DansZamaniFragment


class Dialogs(var context: Context) {

    var point = 0
    var puan_durumu = 0
    var playerpointkey = ""

    fun getMainInfoDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.info_dialog)

        val window: Window = dialog.window ?: return

        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes: WindowManager.LayoutParams = window.attributes
        windowAttributes.gravity = Gravity.CENTER
        window.attributes = windowAttributes
        dialog.setCancelable(false)

        val close: ImageView = window.findViewById(R.id.close)
        val text: TextView = window.findViewById(R.id.text)
        val button: Button = window.findViewById(R.id.button)
        val title_button: Button = window.findViewById(R.id.title_button)

        button.visibility = View.GONE

        close.setOnClickListener { dialog.dismiss() }

        dialog.show()
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
    }

    fun getInfoDialog(main_text: String, button_text: String, mediaPlayer: MediaPlayer? = null, timer: CountDownTimer? = null) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.info_dialog)

        val window: Window = dialog.window ?: return

        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes: WindowManager.LayoutParams = window.attributes
        windowAttributes.gravity = Gravity.CENTER
        window.attributes = windowAttributes
        dialog.setCancelable(false)

        val close: ImageView = window.findViewById(R.id.close)
        val textView: TextView = window.findViewById(R.id.text)
        val button: Button = window.findViewById(R.id.button)
        val title_button: Button = window.findViewById(R.id.title_button)

        close.visibility = View.GONE
        title_button.text = button_text
        textView.text = main_text
        button.setOnClickListener {
            dialog.dismiss()
            mediaPlayer?.start()
        }

        dialog.show()
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
    }

    fun getLeaderBoardDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.leader_board_dialog)

        val window: Window = dialog.window ?: return

        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes: WindowManager.LayoutParams = window.attributes
        windowAttributes.gravity = Gravity.CENTER
        window.attributes = windowAttributes
        dialog.setCancelable(false)

        val button: Button = window.findViewById(R.id.button)
        val player1: TextView = window.findViewById(R.id.player1)
        val player2: TextView = window.findViewById(R.id.player2)
        val player3: TextView = window.findViewById(R.id.player3)
        val player4: TextView = window.findViewById(R.id.player4)
        val player5: TextView = window.findViewById(R.id.player5)
        val player6: TextView = window.findViewById(R.id.player6)
        val point1: TextView = window.findViewById(R.id.point1)
        val point2: TextView = window.findViewById(R.id.point2)
        val point3: TextView = window.findViewById(R.id.point3)
        val point4: TextView = window.findViewById(R.id.point4)
        val point5: TextView = window.findViewById(R.id.point5)
        val point6: TextView = window.findViewById(R.id.point6)

        val preferences = context.getSharedPreferences("players", Context.MODE_PRIVATE)

        val player1_text = preferences.getString("player1", "")
        val player2_text = preferences.getString("player2", "")
        val player3_text = preferences.getString("player3", "")
        val player4_text = preferences.getString("player4", null)
        val player5_text = preferences.getString("player5", null)
        val player6_text = preferences.getString("player6", null)
        val players = preferences.getInt("players", 3)

        val p1point = preferences.getInt("p1point", 0)
        val p2point = preferences.getInt("p2point", 0)
        val p3point = preferences.getInt("p3point", 0)
        val p4point = preferences.getInt("p4point", 0)
        val p5point = preferences.getInt("p5point", 0)
        val p6point = preferences.getInt("p6point", 0)

        when (players) {
            3 -> {
                player1.text = player1_text
                point1.text = p1point.toString()

                player2.text = player2_text
                point2.text = p2point.toString()

                player3.text = player3_text
                point3.text = p3point.toString()

                player4.visibility = View.GONE
                player5.visibility = View.GONE
                player6.visibility = View.GONE

                point4.visibility = View.GONE
                point5.visibility = View.GONE
                point6.visibility = View.GONE
            }
            4 -> {
                player1.text = player1_text
                point1.text = p1point.toString()

                player2.text = player2_text
                point2.text = p2point.toString()

                player3.text = player3_text
                point3.text = p3point.toString()

                player4.text = player4_text
                point4.text = p4point.toString()

                player5.visibility = View.GONE
                player6.visibility = View.GONE

                point5.visibility = View.GONE
                point6.visibility = View.GONE
            }
            5 -> {
                player1.text = player1_text
                point1.text = p1point.toString()

                player2.text = player2_text
                point2.text = p2point.toString()

                player3.text = player3_text
                point3.text = p3point.toString()

                player4.text = player4_text
                point4.text = p4point.toString()

                player5.text = player5_text
                point5.text = p5point.toString()

                player6.visibility = View.GONE
                point6.visibility = View.GONE
            }
            6 -> {
                player1.text = player1_text
                point1.text = p1point.toString()

                player2.text = player2_text
                point2.text = p2point.toString()

                player3.text = player3_text
                point3.text = p3point.toString()

                player4.text = player4_text
                point4.text = p4point.toString()

                player5.text = player5_text
                point5.text = p5point.toString()

                player6.text = player6_text
                point6.text = p6point.toString()
            }
        }

        button.setOnClickListener { dialog.dismiss() }

        dialog.show()
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)

    }

    @SuppressLint("CommitPrefEdits")
    public fun getDansOylamasiDialog(player: String, activity: Activity) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.halk_oylamasi1_dialog)

        val window: Window = dialog.window ?: return

        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes: WindowManager.LayoutParams = window.attributes
        windowAttributes.gravity = Gravity.CENTER
        window.attributes = windowAttributes
        dialog.setCancelable(false)

        val layout1: LinearLayout = window.findViewById(R.id.layout1)
        val layout2: LinearLayout = window.findViewById(R.id.layout2)
        val layout3: LinearLayout = window.findViewById(R.id.layout3)
        val layout4: LinearLayout = window.findViewById(R.id.layout4)
        val layout5: LinearLayout = window.findViewById(R.id.layout5)

        val button: Button = window.findViewById(R.id.button)
        val player1: TextView = window.findViewById(R.id.player1)
        val player2: TextView = window.findViewById(R.id.player2)
        val player3: TextView = window.findViewById(R.id.player3)
        val player4: TextView = window.findViewById(R.id.player4)
        val player5: TextView = window.findViewById(R.id.player5)

        val p1passed: ImageView = window.findViewById(R.id.p1passed)
        val p2passed: ImageView = window.findViewById(R.id.p2passed)
        val p3passed: ImageView = window.findViewById(R.id.p3passed)
        val p4passed: ImageView = window.findViewById(R.id.p4passed)
        val p5passed: ImageView = window.findViewById(R.id.p5passed)

        val p1failed: ImageView = window.findViewById(R.id.p1failed)
        val p2failed: ImageView = window.findViewById(R.id.p2failed)
        val p3failed: ImageView = window.findViewById(R.id.p3failed)
        val p4failed: ImageView = window.findViewById(R.id.p4failed)
        val p5failed: ImageView = window.findViewById(R.id.p5failed)

        val preferences = context.getSharedPreferences("players", Context.MODE_PRIVATE)

        val player1_text = preferences.getString("player1", "")!!
        val player2_text = preferences.getString("player2", "")!!
        val player3_text = preferences.getString("player3", "")!!
        val player4_text = preferences.getString("player4", null)!!
        val player5_text = preferences.getString("player5", null)!!
        val player6_text = preferences.getString("player6", null)!!
        val players = preferences.getInt("players", 3)

        val p1point = preferences.getInt("p1point", 0)
        val p2point = preferences.getInt("p2point", 0)
        val p3point = preferences.getInt("p3point", 0)
        val p4point = preferences.getInt("p4point", 0)
        val p5point = preferences.getInt("p5point", 0)
        val p6point = preferences.getInt("p6point", 0)


        val playerList: Array<String> = arrayOf(
            player1_text,
            player2_text,
            player3_text,
            player4_text,
            player5_text,
            player6_text
        )
        val pointList: Array<Int> = arrayOf(p1point, p2point, p3point, p4point, p5point, p6point)


        for (a in playerList.indices) {
            if (player == playerList[a]) {
                val playernumber = a + 1
                println("a= $a")
                var playerKey = "player$playernumber"
                playerpointkey = "p$playernumber" + "point"
            }
        }


        getPoint(p1passed, p1failed)
        getPoint(p2passed, p2failed)
        getPoint(p3passed, p3failed)
        getPoint(p4passed, p4failed)
        getPoint(p5passed, p5failed)

        when (players) {
            3 -> {
                layout3.visibility = View.GONE
                layout4.visibility = View.GONE
                layout5.visibility = View.GONE
                p3passed.isClickable = false
                p4passed.isClickable = false
                p5passed.isClickable = false

            }
            4 -> {
                layout4.visibility = View.GONE
                layout5.visibility = View.GONE
                p4passed.isClickable = false
                p5passed.isClickable = false

            }
            5 -> {
                layout5.visibility = View.GONE
                p5passed.isClickable = false
            }
        }

        button.setOnClickListener {
            puan_durumu =
                if (!p1failed.isClickable || !p1passed.isClickable && !p2failed.isClickable || !p2passed.isClickable) {
                    1
                } else {
                    0
                }
            when (players) {
                4 -> {
                    puan_durumu = if (!p3failed.isClickable || !p3passed.isClickable) {
                        1
                    } else {
                        0
                    }
                }
                5 -> {
                    puan_durumu =
                        if (!p3failed.isClickable || !p3passed.isClickable && !p4failed.isClickable || !p4passed.isClickable) {
                            1
                        } else {
                            0
                        }
                }
                6 -> {
                    puan_durumu = if (!p3failed.isClickable || !p3passed.isClickable
                        && !p4failed.isClickable || !p4passed.isClickable && !p5failed.isClickable || !p5passed.isClickable
                    ) {
                        1
                    } else {
                        0
                    }
                }
            }
            if (puan_durumu == 1) {
                val currentpoint = preferences.getInt(playerpointkey, 0)
                if (point > 0) {
                    val editor = preferences.edit()
                    editor.putInt(playerpointkey, currentpoint + 15)
                    Toast.makeText(context, "Hadi yine iyisin, kaptın puanı", Toast.LENGTH_LONG).show()
                    editor.apply()
                } else {
                    Toast.makeText(context, "Sorun değil, karpuz da yata yata büyür", Toast.LENGTH_LONG).show()
                }
                dialog.dismiss()
                //val fragment: Fragment = DansZamaniFragment()
               //activity.fragmentManager.beginTransaction().addToBackStack("a")
                //getPlayers(null)
                activity.startActivity(Intent(activity,MainActivity::class.java))
                activity.finish()
                /*
                activity?.let{
                    val intent = Intent (it, MainActivity::class.java)
                    it.startActivity(intent)
                }*/
                    } else {

                Toast.makeText(context, "Hızlı giden atın b.ku seyrek düşer dostum!", Toast.LENGTH_LONG).show()
            }
        }

        when (player) {
            player1_text -> {
                player1.text = player2_text
                player2.text = player3_text

                when (players) {
                    4 -> player3.text = player4_text
                    5 -> {
                        player3.text = player4_text
                        player4.text = player5_text
                    }
                    6 -> {
                        player3.text = player4_text
                        player4.text = player5_text
                        player5.text = player6_text
                    }
                }
            }
            player2_text -> {
                player1.text = player1_text
                player2.text = player3_text

                when (players) {
                    4 -> player3.text = player4_text
                    5 -> {
                        player3.text = player4_text
                        player4.text = player5_text
                    }
                    6 -> {
                        player3.text = player4_text
                        player4.text = player5_text
                        player5.text = player6_text
                    }
                }
            }
            player3_text -> {
                player1.text = player1_text
                player2.text = player2_text

                when (players) {
                    4 -> player3.text = player4_text
                    5 -> {
                        player3.text = player4_text
                        player4.text = player5_text
                    }
                    6 -> {
                        player3.text = player4_text
                        player4.text = player5_text
                        player5.text = player6_text
                    }
                }
            }
            player4_text -> {
                player1.text = player1_text
                player2.text = player2_text

                when (players) {
                    4 -> player3.text = player3_text
                    5 -> {
                        player3.text = player3_text
                        player4.text = player5_text
                    }
                    6 -> {
                        player3.text = player3_text
                        player4.text = player5_text
                        player5.text = player6_text
                    }
                }
            }
            player5_text -> {
                player1.text = player1_text
                player2.text = player2_text

                when (players) {
                    4 -> player3.text = player3_text
                    5 -> {
                        player3.text = player3_text
                        player4.text = player4_text
                    }
                    6 -> {
                        player3.text = player3_text
                        player4.text = player4_text
                        player5.text = player6_text
                    }
                }
            }
            player6_text -> {
                player1.text = player1_text
                player2.text = player2_text

                when (players) {
                    4 -> player3.text = player3_text
                    5 -> {
                        player3.text = player3_text
                        player4.text = player4_text
                    }
                    6 -> {
                        player3.text = player3_text
                        player4.text = player4_text
                        player5.text = player5_text
                    }
                }
            }
        }

        dialog.show()
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
    }

    private fun getPoint(icon: ImageView, icon2: ImageView) {
        icon.setOnClickListener {
            icon.isClickable = false
            icon2.visibility = View.INVISIBLE
            point += 1
        }

        icon2.setOnClickListener {
            icon2.isClickable = false
            icon.visibility = View.INVISIBLE
            point -= 1
        }
    }

    fun checkPlayers(player: String) {
        val preferences = context.getSharedPreferences("players", Context.MODE_PRIVATE)

        val player1_text = preferences.getString("player1", "")!!
        val player2_text = preferences.getString("player2", "")!!
        val player3_text = preferences.getString("player3", "")!!
        val player4_text = preferences.getString("player4", null)!!
        val player5_text = preferences.getString("player5", null)!!
        val player6_text = preferences.getString("player6", null)!!
        val players = preferences.getInt("players", 3)

        val playerList: Array<String> = arrayOf(
            player1_text,
            player2_text,
            player3_text,
            player4_text,
            player5_text,
            player6_text
        )

        for (a in playerList.indices) {
            if (player == playerList[a]) {
                val playernumber = a + 1
                val playerKey = "player$playernumber"
            }
        }

    }

     fun getPlayers(tw: TextView?){
        val preferences = context.getSharedPreferences("players", Context.MODE_PRIVATE)
         var a = 0
             a = preferences.getInt("playernumber", 0)

        val player1 = preferences.getString("player1","")!!
        val player2 = preferences.getString("player2","")!!
        val player3 = preferences.getString("player3","")!!
        val player4 = preferences.getString("player4","")!!
        val player5 = preferences.getString("player5","")!!
        val player6 = preferences.getString("player6","")!!
        val playerList: Array<String> = arrayOf(player1,player2,player3,player4,player5,player6)
        var playernumber = 0

        if (a< playerList.size && playerList[a] != "girilmedi"){
            playernumber = a
            a += 1
        } else{
            a = 0
            playernumber = a
        }
        val editor = preferences.edit()
        editor.putString("currplayer", playerList[playernumber])
         editor.putInt("playernumber", a)
        editor.apply()
        if(tw != null){
            tw.text = playerList[playernumber]
        }
    }

    /*
    fun main(args:Array<String>){
        var mongoClient: MongoClient? = null
        try {
            mongoClient = MongoClient("mongodb+srv://erkan:er12345kan$#er12345kan$#1shotwheel.7lupp.mongodb.net/shotwheel?retryWrites=true&w=majority", 27017)
            println("Kotlin is now connected to MongoDB!")
        } catch (e: MongoException) {
            e.printStackTrace()
        } finally {
            mongoClient!!.close()
        }
    }

     */

}