package com.agency11.shotwheel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.agency11.shotwheel.activity.MainActivity


class Dialogs(var context: Context) {

    var point = 0
    var puan_durumu = 0
    var playerpointkey = ""
    val size = Size(context)

    fun getMainInfoDialog(background: RelativeLayout) {
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

        val cardView: CardView = window.findViewById(R.id.dialog_card)
        val close: ImageView = window.findViewById(R.id.close)
        val text: TextView = window.findViewById(R.id.text)
        val button: Button = window.findViewById(R.id.button)
        val title_button: Button = window.findViewById(R.id.title_button)
        val titlelayout: LinearLayout = window.findViewById(R.id.titlelayout)

        size.setWidth(cardView, 328)
        size.setMargin(titlelayout, 24, 24, 0, 0)
        size.setPadding(title_button, 32, 0, 32, 0)
        size.setButtonSize(title_button, 16)
        size.setMargin(close, 24, 0, 24, 0)
        size.setMargin(text, 24, 32, 24, 0)
        size.setSize(text, 16)
        size.setMargin(button, 24, 32, 24, 24)
        size.setPadding(button, 32, 0, 32, 0)
        size.setButtonSize(button, 16)

        button.visibility = View.GONE

        close.setOnClickListener {
            background.visibility = View.GONE
            dialog.dismiss()
        }

        background.visibility = View.VISIBLE
        dialog.show()
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
        //dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#CC000000")))
    }

    fun getInfoDialog(
        background: RelativeLayout,
        main_text: String,
        button_text: String,
        mediaPlayer: MediaPlayer? = null,
        timer: CountDownTimer? = null
    ) {
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

        val cardView: CardView = window.findViewById(R.id.dialog_card)
        val close: ImageView = window.findViewById(R.id.close)
        val textView: TextView = window.findViewById(R.id.text)
        val button: Button = window.findViewById(R.id.button)
        val title_button: Button = window.findViewById(R.id.title_button)
        val titlelayout: LinearLayout = window.findViewById(R.id.titlelayout)

        size.setWidth(cardView, 328)
        size.setMargin(titlelayout, 24, 24, 0, 0)
        size.setPadding(title_button, 32, 0, 32, 0)
        size.setButtonSize(title_button, 16)
        size.setMargin(close, 24, 0, 24, 0)
        size.setMargin(textView, 24, 32, 24, 0)
        size.setSize(textView, 16)
        size.setMargin(button, 24, 32, 24, 24)
        size.setPadding(button, 32, 0, 32, 0)
        size.setButtonSize(button, 16)

        close.visibility = View.GONE
        title_button.text = button_text
        textView.text = main_text
        title_button.setBackgroundResource(R.drawable.dialog_bluewhite_button)
        title_button.setTextColor(context.resources.getColor(R.color.blue))
        button.setOnClickListener {
            background.visibility = View.GONE
            dialog.dismiss()
            mediaPlayer?.start()
            timer?.start()
        }

        background.visibility = View.VISIBLE
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

        val cardview: CardView = window.findViewById(R.id.dialog_card)
        val button: Button = window.findViewById(R.id.button)
        val title_button: Button = window.findViewById(R.id.title_button)
        val player1: Button = window.findViewById(R.id.player1)
        val player2: Button = window.findViewById(R.id.player2)
        val player3: Button = window.findViewById(R.id.player3)
        val player4: Button = window.findViewById(R.id.player4)
        val player5: Button = window.findViewById(R.id.player5)
        val player6: Button = window.findViewById(R.id.player6)
        val point1: Button = window.findViewById(R.id.point1)
        val point2: Button = window.findViewById(R.id.point2)
        val point3: Button = window.findViewById(R.id.point3)
        val point4: Button = window.findViewById(R.id.point4)
        val point5: Button = window.findViewById(R.id.point5)
        val point6: Button = window.findViewById(R.id.point6)
        val titlelayout: LinearLayout = window.findViewById(R.id.titlelayout)
        val text: TextView = window.findViewById(R.id.text)

        val layout1: LinearLayout = window.findViewById(R.id.layout1)
        val layout2: LinearLayout = window.findViewById(R.id.layout2)
        val layout3: LinearLayout = window.findViewById(R.id.layout3)
        val layout4: LinearLayout = window.findViewById(R.id.layout4)
        val layout5: LinearLayout = window.findViewById(R.id.layout5)
        val layout6: LinearLayout = window.findViewById(R.id.layout6)

        size.setWidth(cardview, 328)
        size.setMargin(titlelayout, 24, 24, 0, 0)
        size.setPadding(title_button, 32, 0, 32, 0)
        size.setButtonSize(title_button, 16)
        size.setMargin(text, 24, 32, 24, 0)
        size.setSize(text, 16)
        size.setMargin(button, 24, 32, 0, 24)
        size.setPadding(button, 32, 0, 32, 0)
        size.setButtonSize(button, 16)

        size.setMargin(layout1, 0, 29, 0, 0)
        size.setMargin(player1, 24, 0, 0, 0)
        size.setPadding(player1, 32, 0, 120, 0)
        size.setButtonSize(player1, 16)
        size.setWidth(point1, 42)
        size.setHeight(point1, 44)
        size.setButtonSize(point1, 16)

        size.setMargin(layout2, 0, 29, 0, 0)
        size.setMargin(player2, 24, 0, 0, 0)
        size.setPadding(player2, 32, 0, 120, 0)
        size.setButtonSize(player2, 16)
        size.setWidth(point2, 42)
        size.setHeight(point2, 44)
        size.setButtonSize(point2, 16)

        size.setMargin(layout3, 0, 29, 0, 0)
        size.setMargin(player3, 24, 0, 0, 0)
        size.setPadding(player3, 32, 0, 120, 0)
        size.setButtonSize(player3, 16)
        size.setWidth(point3, 42)
        size.setHeight(point3, 44)
        size.setButtonSize(point3, 16)

        size.setMargin(layout4, 0, 29, 0, 0)
        size.setMargin(player4, 24, 0, 0, 0)
        size.setPadding(player4, 32, 0, 120, 0)
        size.setButtonSize(player4, 16)
        size.setWidth(point4, 42)
        size.setHeight(point4, 44)
        size.setButtonSize(point4, 16)

        size.setMargin(layout5, 0, 29, 0, 0)
        size.setMargin(player5, 24, 0, 0, 0)
        size.setPadding(player5, 32, 0, 120, 0)
        size.setButtonSize(player5, 16)
        size.setWidth(point5, 42)
        size.setHeight(point5, 44)
        size.setButtonSize(point5, 16)

        size.setMargin(layout6, 0, 29, 0, 0)
        size.setMargin(player6, 24, 0, 0, 0)
        size.setPadding(player6, 32, 0, 120, 0)
        size.setButtonSize(player6, 16)
        size.setWidth(point6, 42)
        size.setHeight(point6, 44)
        size.setButtonSize(point6, 16)

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

    fun getOylamaDialog(player: String, text: String, activity: Activity, game_point: Int) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.halk_oylamasi2_dialog)

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

        val cardview: CardView = window.findViewById(R.id.dialog_card)
        val title_button: Button = window.findViewById(R.id.title_button)
        val button: Button = window.findViewById(R.id.button)
        val textv: TextView = window.findViewById(R.id.text)
        val player1: Button = window.findViewById(R.id.player1)
        val player2: Button = window.findViewById(R.id.player2)
        val player3: Button = window.findViewById(R.id.player3)
        val player4: Button = window.findViewById(R.id.player4)
        val player5: Button = window.findViewById(R.id.player5)
        val player6: Button = window.findViewById(R.id.player6)
        val title2: TextView = window.findViewById(R.id.title2)

        val layout1: LinearLayout = window.findViewById(R.id.layout1)
        val layout2: LinearLayout = window.findViewById(R.id.layout2)
        val layout3: LinearLayout = window.findViewById(R.id.layout3)
        val layout4: LinearLayout = window.findViewById(R.id.layout4)
        val layout5: LinearLayout = window.findViewById(R.id.layout5)
        val layout6: LinearLayout = window.findViewById(R.id.layout6)

        val number1: Button = window.findViewById(R.id.number1)
        val number2: Button = window.findViewById(R.id.number2)
        val number3: Button = window.findViewById(R.id.number3)
        val number4: Button = window.findViewById(R.id.number4)
        val number5: Button = window.findViewById(R.id.number5)
        val number6: Button = window.findViewById(R.id.number6)

        size.setMargin(title2, 24, 12, 45, 0)
        size.setSize(title2, 14)
        size.setPadding(title_button, 24, 24, 0, 0)
        size.setMargin(title_button, 32, 0, 32, 0)
        size.setButtonSize(title_button, 16)
        size.setMargin(textv, 24, 32, 24, 0)
        size.setSize(textv, 16)
        size.setMargin(button, 24, 32, 0, 24)
        size.setPadding(button, 32, 12, 32, 12)
        size.setButtonSize(button, 16)

        size.setMargin(layout1, 0, 29, 0, 0)
        size.setMargin(player1, 24, 0, 0, 0)
        size.setPadding(player1, 32, 0, 120, 0)
        size.setButtonSize(player1, 16)
        size.setWidth(number1, 42)
        size.setHeight(number1, 44)
        size.setButtonSize(number1, 16)

        size.setMargin(layout2, 0, 29, 0, 0)
        size.setMargin(player2, 24, 0, 0, 0)
        size.setPadding(player2, 32, 0, 120, 0)
        size.setButtonSize(player2, 16)
        size.setWidth(number2, 42)
        size.setHeight(number2, 44)
        size.setButtonSize(number2, 16)

        size.setMargin(layout3, 0, 29, 0, 0)
        size.setMargin(player3, 24, 0, 0, 0)
        size.setPadding(player3, 32, 0, 120, 0)
        size.setButtonSize(player3, 16)
        size.setWidth(number3, 42)
        size.setHeight(number3, 44)
        size.setButtonSize(number3, 16)

        size.setMargin(layout4, 0, 29, 0, 0)
        size.setMargin(player4, 24, 0, 0, 0)
        size.setPadding(player4, 32, 0, 120, 0)
        size.setButtonSize(player4, 16)
        size.setWidth(number4, 42)
        size.setHeight(number4, 44)
        size.setButtonSize(number4, 16)

        size.setMargin(layout5, 0, 29, 0, 0)
        size.setMargin(player5, 24, 0, 0, 0)
        size.setPadding(player5, 32, 0, 120, 0)
        size.setButtonSize(player5, 16)
        size.setWidth(number5, 42)
        size.setHeight(number5, 44)
        size.setButtonSize(number5, 16)

        size.setMargin(layout6, 0, 29, 0, 0)
        size.setMargin(player6, 24, 0, 0, 0)
        size.setPadding(player6, 32, 0, 120, 0)
        size.setButtonSize(player6, 16)
        size.setWidth(number6, 42)
        size.setHeight(number6, 44)
        size.setButtonSize(number6, 16)


        var point = 0

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
                println("a= $a")
                var playerKey = "player$playernumber"
                playerpointkey = "p$playernumber" + "point"
            }
        }

        player1.text = player1_text
        player2.text = player2_text
        player3.text = player3_text
        textv.text = text

        when (players) {
            3 -> {
                layout4.visibility = View.GONE
                layout5.visibility = View.GONE
                layout6.visibility = View.GONE
            }
            4 -> {
                layout5.visibility = View.GONE
                layout6.visibility = View.GONE
                player4.text = player4_text
            }
            5 -> {
                layout6.visibility = View.GONE
                player4.text = player4_text
                player5.text = player5_text
            }
            6 -> {
                player4.text = player4_text
                player5.text = player5_text
                player6.text = player6_text
            }
        }
        var point1 = 0
        var point2 = 0
        var point3 = 0
        var point4 = 0
        var point5 = 0
        var point6 = 0
        var clickable = 1

        player1.setOnClickListener {
            if (clickable == 1) {
                point += 1
                point1 += 1
                number1.text = "$point1"
                if (point == players) {
                    clickable = 0
                }
            } else {
                Toast.makeText(context, "Oylama Tamamlandı", Toast.LENGTH_SHORT).show()
            }
        }

        player2.setOnClickListener {
            if (clickable == 1) {
                point += 1
                point2 += 1
                number2.text = "$point2"
                if (point == players) {
                    clickable = 0
                }
            } else {
                Toast.makeText(context, "Oylama Tamamlandı", Toast.LENGTH_SHORT).show()
            }
        }

        player3.setOnClickListener {
            if (clickable == 1) {
                point += 1
                point3 += 1
                number3.text = "$point3"
                if (point == players) {
                    clickable = 0
                }
            } else {
                Toast.makeText(context, "Oylama Tamamlandı", Toast.LENGTH_SHORT).show()
            }
        }

        player4.setOnClickListener {
            if (clickable == 1) {
                point += 1
                point4 += 1
                number4.text = "$point4"
                if (point == players) {
                    clickable = 0
                }
            } else {
                Toast.makeText(context, "Oylama Tamamlandı", Toast.LENGTH_SHORT).show()
            }
        }

        player5.setOnClickListener {
            if (clickable == 1) {
                point += 1
                point5 += 1
                number5.text = "$point5"
                if (point == players) {
                    clickable = 0
                }
            } else {
                Toast.makeText(context, "Oylama Tamamlandı", Toast.LENGTH_SHORT).show()
            }
        }

        player6.setOnClickListener {
            if (clickable == 1) {
                point += 1
                point6 += 1
                number6.text = "$point6"
                if (point == players) {
                    clickable = 0
                }
            } else {
                Toast.makeText(context, "Oylama Tamamlandı", Toast.LENGTH_SHORT).show()
            }
        }

        button.setOnClickListener {
            if (clickable == 0) {
                val pointList: Array<Int> = arrayOf(point1, point2, point3, point4, point5, point6)

                val max = pointList.maxOrNull() ?: 0
                if (max != 0) {
                    when (max) {
                        point1 -> {
                            if (max != point2 && max != point3 && max != point4 && max != point5 && max != point6) {
                                addPoint(player1_text, game_point)
                                Toast.makeText(
                                    context,
                                    "Hadi yine iyisin, kaptın puanı",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sorun değil, karpuz da yata yata büyür",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        point2 -> {
                            if (point1 != max && point3 != max && point4 != max && point5 != max && point6 != max) {
                                addPoint(player2_text, game_point)
                                Toast.makeText(
                                    context,
                                    "Hadi yine iyisin, kaptın puanı",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sorun değil, karpuz da yata yata büyür",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        point3 -> {
                            if (point2 != max && point1 != max && point4 != max && point5 != max && point6 != max) {
                                addPoint(player3_text, game_point)
                                Toast.makeText(
                                    context,
                                    "Hadi yine iyisin, kaptın puanı",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sorun değil, karpuz da yata yata büyür",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        point4 -> {
                            if (point2 != max && point1 != max && point3 != max && point5 != max && point6 != max) {
                                addPoint(player4_text, game_point)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sorun değil, karpuz da yata yata büyür",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        point5 -> {
                            if (point2 != max && point1 != max && point4 != max && point3 != max && point6 != max) {
                                addPoint(player5_text, game_point)
                                Toast.makeText(
                                    context,
                                    "Hadi yine iyisin, kaptın puanı",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sorun değil, karpuz da yata yata büyür",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        point6 -> {
                            if (point2 != max && point1 != max && point4 != max && point5 != max && point3 != max) {
                                addPoint(player6_text, game_point)
                                Toast.makeText(
                                    context,
                                    "Hadi yine iyisin, kaptın puanı",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Sorun değil, karpuz da yata yata büyür",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    dialog.dismiss()
                    activity.startActivity(Intent(context, MainActivity::class.java))
                    activity.finish()
                } else {
                    Toast.makeText(
                        context,
                        "Sorun değil, karpuz da yata yata büyür",
                        Toast.LENGTH_LONG
                    ).show()
                    dialog.dismiss()
                    activity.startActivity(Intent(context, MainActivity::class.java))
                    activity.finish()
                }

            } else {
                Toast.makeText(
                    context,
                    "Hızlı giden atın b.ku seyrek düşer dostum!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

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
        val cardview: CardView = window.findViewById(R.id.dialog_card)

        val button: Button = window.findViewById(R.id.button)
        val title_button: Button = window.findViewById(R.id.title_button)
        val text: TextView = window.findViewById(R.id.text)
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

        size.setWidth(cardview, 328)
        size.setMargin(button, 24, 32, 0, 24)
        size.setPadding(button, 32, 0, 32, 0)
        size.setButtonSize(button, 16)
        size.setMargin(title_button, 23, 23, 0, 0)
        size.setPadding(title_button, 32, 0, 32, 0)
        size.setButtonSize(title_button, 16)

        size.setSize(text, 16)
        size.setMargin(text, 24, 32, 24, 0)
        size.setMargin(layout1, 36, 36, 0, 0)
        size.setMargin(layout2, 36, 32, 0, 0)
        size.setMargin(layout3, 36, 32, 0, 0)
        size.setMargin(layout4, 36, 32, 0, 0)
        size.setMargin(layout5, 36, 32, 0, 0)

        size.setSize(player1, 20)
        size.setMargin(player1, 24, 0, 0, 0)
        size.setSize(player2, 20)
        size.setMargin(player2, 24, 0, 0, 0)
        size.setSize(player3, 20)
        size.setMargin(player3, 24, 0, 0, 0)
        size.setSize(player4, 20)
        size.setMargin(player4, 24, 0, 0, 0)
        size.setSize(player5, 20)
        size.setMargin(player5, 24, 0, 0, 0)

        size.setWidth(p1passed, 32)
        size.setHeight(p1passed, 32)
        size.setWidth(p1failed, 32)
        size.setHeight(p1failed, 32)
        size.setMargin(p1failed, 24, 0, 24, 0)

        size.setWidth(p2passed, 32)
        size.setHeight(p2passed, 32)
        size.setWidth(p2failed, 32)
        size.setHeight(p2failed, 32)
        size.setMargin(p2failed, 24, 0, 24, 0)

        size.setWidth(p3passed, 32)
        size.setHeight(p3passed, 32)
        size.setWidth(p3failed, 32)
        size.setHeight(p3failed, 32)
        size.setMargin(p3failed, 24, 0, 24, 0)

        size.setWidth(p4passed, 32)
        size.setHeight(p4passed, 32)
        size.setWidth(p4failed, 32)
        size.setHeight(p4failed, 32)
        size.setMargin(p4failed, 24, 0, 24, 0)

        size.setWidth(p5passed, 32)
        size.setHeight(p5passed, 32)
        size.setWidth(p5failed, 32)
        size.setHeight(p5failed, 32)
        size.setMargin(p5failed, 24, 0, 24, 0)


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
                    editor.putInt(playerpointkey, currentpoint + 20)
                    Toast.makeText(context, "Hadi yine iyisin, kaptın puanı", Toast.LENGTH_LONG)
                        .show()
                    editor.apply()
                } else {
                    Toast.makeText(
                        context,
                        "Sorun değil, karpuz da yata yata büyür",
                        Toast.LENGTH_LONG
                    ).show()
                }
                dialog.dismiss()
                //val fragment: Fragment = DansZamaniFragment()
                //activity.fragmentManager.beginTransaction().addToBackStack("a")
                //getPlayers(null)
                activity.startActivity(Intent(activity, MainActivity::class.java))
                activity.finish()
                /*
                activity?.let{
                    val intent = Intent (it, MainActivity::class.java)
                    it.startActivity(intent)
                }*/
            } else {

                Toast.makeText(
                    context,
                    "Hızlı giden atın b.ku seyrek düşer dostum!",
                    Toast.LENGTH_LONG
                ).show()
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

    fun addPoint(player: String, point: Int) {
        val preferences = context.getSharedPreferences("players", Context.MODE_PRIVATE)

        val player1_text = preferences.getString("player1", "")!!
        val player2_text = preferences.getString("player2", "")!!
        val player3_text = preferences.getString("player3", "")!!
        val player4_text = preferences.getString("player4", null)!!
        val player5_text = preferences.getString("player5", null)!!
        val player6_text = preferences.getString("player6", null)!!


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
                println("a= $a")
                var playerKey = "player$playernumber"
                playerpointkey = "p$playernumber" + "point"
            }
        }
        val currentpoint = preferences.getInt(playerpointkey, 0)
        val editor = preferences.edit()
        editor.putInt(playerpointkey, currentpoint + point)
        editor.apply()
    }

    fun getEndDialog(main_text: String, button2_text: String, activity: Activity) {
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

        val cardView: CardView = window.findViewById(R.id.dialog_card)
        val close: ImageView = window.findViewById(R.id.close)
        val text: TextView = window.findViewById(R.id.text)
        val button: Button = window.findViewById(R.id.button)
        val title_button: Button = window.findViewById(R.id.title_button)
        val titlelayout: LinearLayout = window.findViewById(R.id.titlelayout)

        size.setWidth(cardView, 328)
        size.setMargin(titlelayout, 24, 24, 0, 0)
        size.setPadding(title_button, 32, 0, 32, 0)
        size.setButtonSize(title_button, 16)
        size.setMargin(close, 24, 0, 24, 0)
        size.setMargin(text, 24, 32, 24, 0)
        size.setSize(text, 16)
        size.setMargin(button, 24, 32, 24, 24)
        size.setPadding(button, 32, 0, 32, 0)
        size.setButtonSize(button, 16)

        close.visibility = View.GONE
        title_button.text = "Süre Doldu!"
        text.text = main_text
        title_button.setBackgroundResource(R.drawable.dialog_redwhite_button)
        title_button.setTextColor(context.resources.getColor(R.color.red))
        button.text = button2_text

        button.setOnClickListener {
            dialog.dismiss()
            activity.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
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

    fun getPlayers(tw: TextView?) {
        val preferences = context.getSharedPreferences("players", Context.MODE_PRIVATE)
        var a = 0
        a = preferences.getInt("playernumber", 0)

        val player1 = preferences.getString("player1", "")!!
        val player2 = preferences.getString("player2", "")!!
        val player3 = preferences.getString("player3", "")!!
        val player4 = preferences.getString("player4", "")!!
        val player5 = preferences.getString("player5", "")!!
        val player6 = preferences.getString("player6", "")!!
        val playerList: Array<String> =
            arrayOf(player1, player2, player3, player4, player5, player6)
        var playernumber = 0

        if (a < playerList.size && playerList[a] != "girilmedi") {
            playernumber = a
            a += 1
        } else {
            a = 0
            playernumber = a
        }
        val editor = preferences.edit()
        editor.putString("currplayer", playerList[playernumber])
        editor.putInt("playernumber", a)
        editor.apply()
        if (tw != null) {
            tw.text = playerList[playernumber]
        }
    }

    /*
    suspend fun kmongo(){
        val client = org.litote.kmongo.reactivestreams.KMongo.createClient().coroutine
        val database = client.getDatabase("shotwheel")

        val col = database.getCollection<BenKimim>()
        col.insertOne(BenKimim(7,"a","a"))

        col.findOne(BenKimim::_id eq 1)
    }

    */
    /*
    fun main(){
        var mongoClient: MongoClient? = null
        try {
            mongoClient = MongoClient("78.175.180.226", 32)
            println("Kotlin is now connected to MongoDB!")
           val mongo =  mongoClient.getDatabase("shotwheel")

            val collection = mongo.getCollection("ben_kimim")

                    mongo.createCollection("asd")

            //val ben_kimim: List<BenKimim> = collection.find().toList()

            val a: MongoCollection<BenKimim> = mongo.getCollection("ben_kimim", BenKimim::class.java)

            val find = a.find(Document("kelime","Charles Darvin"))

            println(collection.namespace)
        } catch (e: MongoException) {
            e.printStackTrace()
        } finally {
            mongoClient!!.close()
        }
    }

     */

}
