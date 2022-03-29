package com.agency11.shotwheel

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.widget.*
import com.agency11.shotwheel.databinding.ActivityPrepareScreenBinding
import kotlin.properties.Delegates

class PrepareScreen : AppCompatActivity() {

    lateinit var binding: ActivityPrepareScreenBinding
     lateinit var edit4: RelativeLayout
    lateinit var edit5: RelativeLayout
    lateinit var edit6: RelativeLayout
    var players = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrepareScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edit4 = binding.edit4
        edit5 = binding.edit5
        edit6 = binding.edit6

        binding.chooseButton.setOnClickListener {
            choosePlayerDialog()
        }

        val start: Button = binding.startButton
        start.setOnClickListener {
            val et1_text = binding.et1.text.toString()
            val et2_text = binding.et2.text.toString()
            val et3_text = binding.et3.text.toString()
            val et4_text = binding.et4.text.toString()
            val et5_text = binding.et5.text.toString()
            val et6_text = binding.et6.text.toString()


            var preferences = getSharedPreferences("players", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            if (TextUtils.isEmpty(et1_text) || TextUtils.isEmpty(et2_text) || TextUtils.isEmpty(et3_text)) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_LONG).show()
            } else {
                editor.putString("player1", et1_text)
                editor.putString("player2", et2_text)
                editor.putString("player3", et3_text)
                when (players) {
                    3 ->{
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    4 -> {
                        if (TextUtils.isEmpty(et4_text)) {
                            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            editor.putString("player4", et4_text)
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                    5 -> {
                        if (TextUtils.isEmpty(et4_text) || TextUtils.isEmpty(et5_text)) {
                            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            editor.putString("player4", et4_text)
                            editor.putString("player5", et5_text)
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                    6 -> {
                        if (TextUtils.isEmpty(et4_text) || TextUtils.isEmpty(et5_text) || TextUtils.isEmpty(
                                et6_text
                            )
                        ) {
                            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            editor.putString("player4", et4_text)
                            editor.putString("player5", et5_text)
                            editor.putString("player6", et6_text)
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                }
                editor.apply()
            }
        }

    }


    private fun choosePlayerDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.choose_player_dialog)

        val window: Window = dialog.window ?: return

        val tree: LinearLayout = window.findViewById(R.id.treeplayer)
        val text1: TextView = window.findViewById(R.id.player_text1)
        val number1: TextView = window.findViewById(R.id.player_number1)

        val four: LinearLayout = window.findViewById(R.id.fourplayer)
        val text2: TextView = window.findViewById(R.id.player_text2)
        val number2: TextView = window.findViewById(R.id.player_number2)

        val five: LinearLayout = window.findViewById(R.id.fiveplayer)
        val text3: TextView = window.findViewById(R.id.player_text3)
        val number3: TextView = window.findViewById(R.id.player_number3)

        val six: LinearLayout = window.findViewById(R.id.sixplayer)
        val text4: TextView = window.findViewById(R.id.player_text4)
        val number4: TextView = window.findViewById(R.id.player_number4)

        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes: WindowManager.LayoutParams = window.attributes
        windowAttributes.gravity = Gravity.BOTTOM
        window.attributes = windowAttributes
        dialog.setCancelable(true)

        when (players) {
            3 -> {
                tree.setBackgroundResource(R.drawable.dialog_button_background)
                four.setBackgroundResource(R.drawable.dialog_button_background_2)
                five.setBackgroundResource(R.drawable.dialog_button_background_2)
                six.setBackgroundResource(R.drawable.dialog_button_background_2)

                text1.setTextColor(resources.getColor(R.color.light_orange))
                number1.setTextColor(resources.getColor(R.color.white))
                edit4.visibility = View.GONE
                edit5.visibility = View.GONE
                edit6.visibility = View.GONE
            }
            4 -> {
                tree.setBackgroundResource(R.drawable.dialog_button_background_2)
                four.setBackgroundResource(R.drawable.dialog_button_background)
                five.setBackgroundResource(R.drawable.dialog_button_background_2)
                six.setBackgroundResource(R.drawable.dialog_button_background_2)

                text1.setTextColor(resources.getColor(R.color.gray))
                number1.setTextColor(resources.getColor(R.color.black))
                text2.setTextColor(resources.getColor(R.color.light_orange))
                number2.setTextColor(resources.getColor(R.color.white))
                text3.setTextColor(resources.getColor(R.color.gray))
                number3.setTextColor(resources.getColor(R.color.black))
                text4.setTextColor(resources.getColor(R.color.gray))
                number4.setTextColor(resources.getColor(R.color.black))

                edit4.visibility = View.VISIBLE
                edit5.visibility = View.GONE
                edit6.visibility = View.GONE
            }
            5 -> {
                tree.setBackgroundResource(R.drawable.dialog_button_background_2)
                four.setBackgroundResource(R.drawable.dialog_button_background_2)
                five.setBackgroundResource(R.drawable.dialog_button_background)
                six.setBackgroundResource(R.drawable.dialog_button_background_2)

                text1.setTextColor(resources.getColor(R.color.gray))
                number1.setTextColor(resources.getColor(R.color.black))
                text2.setTextColor(resources.getColor(R.color.gray))
                number2.setTextColor(resources.getColor(R.color.black))
                text3.setTextColor(resources.getColor(R.color.light_orange))
                number3.setTextColor(resources.getColor(R.color.white))
                text4.setTextColor(resources.getColor(R.color.gray))
                number4.setTextColor(resources.getColor(R.color.black))

                edit4.visibility = View.VISIBLE
                edit5.visibility = View.VISIBLE
                edit6.visibility = View.GONE
            }
            6 -> {
                tree.setBackgroundResource(R.drawable.dialog_button_background_2)
                four.setBackgroundResource(R.drawable.dialog_button_background_2)
                five.setBackgroundResource(R.drawable.dialog_button_background_2)
                six.setBackgroundResource(R.drawable.dialog_button_background)

                text1.setTextColor(resources.getColor(R.color.gray))
                number1.setTextColor(resources.getColor(R.color.black))
                text2.setTextColor(resources.getColor(R.color.gray))
                number2.setTextColor(resources.getColor(R.color.black))
                text3.setTextColor(resources.getColor(R.color.gray))
                number3.setTextColor(resources.getColor(R.color.black))
                text4.setTextColor(resources.getColor(R.color.light_orange))
                number4.setTextColor(resources.getColor(R.color.white))

                edit4.visibility = View.VISIBLE
                edit5.visibility = View.VISIBLE
                edit6.visibility = View.VISIBLE
            }
        }

        tree.setOnClickListener {
            tree.setBackgroundResource(R.drawable.dialog_button_background)
            four.setBackgroundResource(R.drawable.dialog_button_background_2)
            five.setBackgroundResource(R.drawable.dialog_button_background_2)
            six.setBackgroundResource(R.drawable.dialog_button_background_2)

            text1.setTextColor(resources.getColor(R.color.light_orange))
            number1.setTextColor(resources.getColor(R.color.white))
            text2.setTextColor(resources.getColor(R.color.gray))
            number2.setTextColor(resources.getColor(R.color.black))
            text3.setTextColor(resources.getColor(R.color.gray))
            number3.setTextColor(resources.getColor(R.color.black))
            text4.setTextColor(resources.getColor(R.color.gray))
            number4.setTextColor(resources.getColor(R.color.black))

            edit4.visibility = View.GONE
            edit5.visibility = View.GONE
            edit6.visibility = View.GONE
            players = 3
        }

        four.setOnClickListener {
            tree.setBackgroundResource(R.drawable.dialog_button_background_2)
            four.setBackgroundResource(R.drawable.dialog_button_background)
            five.setBackgroundResource(R.drawable.dialog_button_background_2)
            six.setBackgroundResource(R.drawable.dialog_button_background_2)

            text1.setTextColor(resources.getColor(R.color.gray))
            number1.setTextColor(resources.getColor(R.color.black))
            text2.setTextColor(resources.getColor(R.color.light_orange))
            number2.setTextColor(resources.getColor(R.color.white))
            text3.setTextColor(resources.getColor(R.color.gray))
            number3.setTextColor(resources.getColor(R.color.black))
            text4.setTextColor(resources.getColor(R.color.gray))
            number4.setTextColor(resources.getColor(R.color.black))

            edit4.visibility = View.VISIBLE
            edit5.visibility = View.GONE
            edit6.visibility = View.GONE
            players = 4
        }

        five.setOnClickListener {
            tree.setBackgroundResource(R.drawable.dialog_button_background_2)
            four.setBackgroundResource(R.drawable.dialog_button_background_2)
            five.setBackgroundResource(R.drawable.dialog_button_background)
            six.setBackgroundResource(R.drawable.dialog_button_background_2)

            text1.setTextColor(resources.getColor(R.color.gray))
            number1.setTextColor(resources.getColor(R.color.black))
            text2.setTextColor(resources.getColor(R.color.gray))
            number2.setTextColor(resources.getColor(R.color.black))
            text3.setTextColor(resources.getColor(R.color.light_orange))
            number3.setTextColor(resources.getColor(R.color.white))
            text4.setTextColor(resources.getColor(R.color.gray))
            number4.setTextColor(resources.getColor(R.color.black))

            edit4.visibility = View.VISIBLE
            edit5.visibility = View.VISIBLE
            edit6.visibility = View.GONE
            players = 5
        }

        six.setOnClickListener {
            tree.setBackgroundResource(R.drawable.dialog_button_background_2)
            four.setBackgroundResource(R.drawable.dialog_button_background_2)
            five.setBackgroundResource(R.drawable.dialog_button_background_2)
            six.setBackgroundResource(R.drawable.dialog_button_background)

            text1.setTextColor(resources.getColor(R.color.gray))
            number1.setTextColor(resources.getColor(R.color.black))
            text2.setTextColor(resources.getColor(R.color.gray))
            number2.setTextColor(resources.getColor(R.color.black))
            text3.setTextColor(resources.getColor(R.color.gray))
            number3.setTextColor(resources.getColor(R.color.black))
            text4.setTextColor(resources.getColor(R.color.light_orange))
            number4.setTextColor(resources.getColor(R.color.white))

            edit4.visibility = View.VISIBLE
            edit5.visibility = View.VISIBLE
            edit6.visibility = View.VISIBLE
            players = 6
        }

        dialog.show()
        dialog.window!!.setWindowAnimations(R.style.AnimationPopup)
        dialog.window!!.clearFlags(FLAG_DIM_BEHIND)
    }

}
