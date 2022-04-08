package com.agency11.shotwheel.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.agency11.shotwheel.R
import com.agency11.shotwheel.Size
import com.agency11.shotwheel.databinding.ActivityPrepareScreenBinding


class PrepareScreen : AppCompatActivity() {

    lateinit var binding: ActivityPrepareScreenBinding
     lateinit var edit4: RelativeLayout
    lateinit var edit5: RelativeLayout
    lateinit var edit6: RelativeLayout
    lateinit var playerList: MutableSet<String>
    var players = 3

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrepareScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val size = Size(this)

        size.setMargin(binding.PrepareUpperLinear,16,50,0,0)
        size.setSize(binding.players,18)
        size.setWidth(binding.chooseButton,75)
        size.setHeight(binding.chooseButton,35)
        size.setMargin(binding.chooseButton,66,0,14,0)
        size.setSize(binding.chooseButton,16)
        size.setMargin(binding.PrepareLowerLinear,0,32,0,0)
        size.setMargin(binding.PlayerNameTextView,16,0,0,12)
        size.setSize(binding.PlayerNameTextView,14)
        size.setMargin(binding.edit1,16,0,16,0)
        size.setMargin(binding.nameTextView,16,0,0,0)
        size.setSize(binding.nameTextView,14)
        size.setHeight(binding.et1,40)
        size.setMargin(binding.et1,10,4,4,4)
        size.setPadding(binding.et1,5,5,5,5)
        size.setMargin(binding.edit2,16,16,16,0)
        size.setMargin(binding.nameTextView2,16,0,0,0)
        size.setSize(binding.nameTextView2,14)
        size.setHeight(binding.et2,40)
        size.setMargin(binding.et2,10,4,4,4)
        size.setPadding(binding.et2,5,5,5,5)
        size.setMargin(binding.edit3,16,16,16,0)
        size.setMargin(binding.nameTextView3,16,0,0,0)
        size.setSize(binding.nameTextView3,14)
        size.setHeight(binding.et3,40)
        size.setMargin(binding.et3,10,4,4,4)
        size.setPadding(binding.et3,5,5,5,5)
        size.setMargin(binding.edit4,16,16,16,0)
        size.setMargin(binding.nameTextView4,16,0,0,0)
        size.setSize(binding.nameTextView4,14)
        size.setHeight(binding.et4,40)
        size.setMargin(binding.et4,10,4,4,4)
        size.setPadding(binding.et4,5,5,5,5)
        size.setMargin(binding.edit5,16,16,16,0)
        size.setMargin(binding.nameTextView5,16,0,0,0)
        size.setSize(binding.nameTextView5,14)
        size.setHeight(binding.et5,40)
        size.setMargin(binding.et5,10,4,4,4)
        size.setPadding(binding.et5,5,5,5,5)
        size.setMargin(binding.edit6,16,16,16,0)
        size.setMargin(binding.nameTextView6,16,0,0,0)
        size.setSize(binding.nameTextView6,14)
        size.setHeight(binding.et6,40)
        size.setMargin(binding.et6,10,4,4,4)
        size.setPadding(binding.et6,5,5,5,5)
        size.setWidth(binding.startButton,144)
        size.setHeight(binding.startButton,35)
        size.setMargin(binding.startButton,0,83,0,0)
        size.setSize(binding.startButton,16)
        



        edit4 = binding.edit4
        edit5 = binding.edit5
        edit6 = binding.edit6

        binding.chooseButton.setOnClickListener {
            choosePlayerDialog()
        }
        val start: Button = binding.startButton
        start.setOnClickListener {
            val et1_text = binding.et1.text.toString().trim()

            val et2_text = binding.et2.text.toString().trim()
            val et3_text = binding.et3.text.toString().trim()
            val et4_text = binding.et4.text.toString().trim()
            val et5_text = binding.et5.text.toString().trim()
            val et6_text = binding.et6.text.toString().trim()


            var preferences = getSharedPreferences("players", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            if (TextUtils.isEmpty(et1_text) || TextUtils.isEmpty(et2_text) || TextUtils.isEmpty(et3_text)) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_LONG).show()
            } else {
                editor.putString("player1", et1_text)
                editor.putString("player2", et2_text)
                editor.putString("player3", et3_text)
                editor.putString("player4", "girilmedi")
                editor.putString("player5", "girilmedi")
                editor.putString("player6", "girilmedi")
                editor.putInt("p1point",0)
                editor.putInt("p2point",0)
                editor.putInt("p3point",0)
                editor.putInt("p4point",0)
                editor.putInt("p5point",0)
                editor.putInt("p6point",0)
                editor.putInt("players",3)
                editor.putInt("playernumber", 0)
                editor.putInt("game",0)
                editor.putInt("timer",0)
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
                            editor.putInt("players",4)
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
                            editor.putInt("players",5)
                            playerList = mutableSetOf(et1_text,et2_text,et3_text,et4_text,et5_text)
                            editor.putStringSet("playerList", playerList)
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
                            editor.putInt("players",6)
                            //playerList = mutableSetOf(et1_text,et2_text,et3_text,et4_text,et5_text,et6_text)
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

    var backbtn = 0

    override fun onBackPressed() {
        backbtn += 1
        if (backbtn == 2){
            finish()
        } else{
            Toast.makeText(this,"Oyundan çıkmak için bir daha tıklayın",Toast.LENGTH_LONG).show()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val ret = super.dispatchTouchEvent(ev)
        ev?.let { event ->
            if (event.action == MotionEvent.ACTION_UP) {
                currentFocus?.let { view ->
                    if (view is EditText) {
                        val touchCoordinates = IntArray(2)
                        view.getLocationOnScreen(touchCoordinates)
                        val x: Float = event.rawX + view.getLeft() - touchCoordinates[0]
                        val y: Float = event.rawY + view.getTop() - touchCoordinates[1]
                        if (x < view.getLeft() || x >= view.getRight() || y < view.getTop() || y > view.getBottom()) {
                            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(view.windowToken, 0)
                            view.clearFocus()
                        }
                    }
                }
            }
        }
        return ret
    }

}
