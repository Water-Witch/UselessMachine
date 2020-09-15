package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
// MainActivity extends AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //can't construct interfaces, instead make a class that implements it
        //lambda lets u make a fxn without a name that can
        // stank in for an interface

        //the one fxn that View.OnClicklistenher has is onClick(v: View!)
        //this lambda below is implementing that one fxn onClick without really
        //mentioning it explicitly
        //the one paramenter is referenced by "it"
        button_main_look_busy.setOnClickListener {
            Toast.makeText(this, "hello, this is the text on the button ${(it as Button).text.toString()}",
                Toast.LENGTH_SHORT).show()

        }
// Math.random() * number of choices + min value  proper way
        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            val message = if (isChecked) "Switch is On" else "Switch is Off"
            Toast.makeText(this, message , Toast.LENGTH_SHORT).show()
            //or Toast.makeText(context: this, text:"The button is : ${isChecked.toString()}, Toast.Length_Short).show()
            //toast status of button(checked or not checked)
            // if button is checked, uncheck
            if(isChecked){
                //Thread.sleep is illegal on the main UI thread
                //janky app -- when it doesn't really respond to clicks and taps immediately
                //countdowntimer is effectively making a separate thread to keep track of time
                val difference = (Math.random() * 4500 + 500).toLong()
                val uncheckerTimer = object: CountDownTimer(difference, 100){
                    override fun onFinish() {
                        switch_main_useless.isChecked = false
                        layout_main.setBackgroundColor(Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
                    }

                    override fun onTick(millisRemaining: Long) {
                        if(!switch_main_useless.isChecked){
                            cancel()
                        }
                    }
                }
                uncheckerTimer.start()
            }

        }
        button_main_self_destruct.setOnClickListener {
            val timer = object: CountDownTimer(10000, 100) {

                override fun onFinish() {
                    button_main_self_destruct.isEnabled = false
                    finish()
                }

                override fun onTick(millisUntilFinished: Long) {
                    var timeLeft = (millisUntilFinished / 500).toInt()
                    button_main_self_destruct.text = (timeLeft / 2).toString()
                    Log.d("BOOM in:", "$timeLeft")

                    if ((timeLeft / 2).toInt() > 7) {
                        if ((timeLeft / 2) % 2 == 0) {
                            layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                        } else {
                            layout_main.setBackgroundColor(Color.rgb(0, 0, 0))
                        }
                    }
                    if (((timeLeft / 2).toInt() < 7) && (timeLeft / 2).toInt() >= 0) {
                        if ((timeLeft) % 2 == 0) {
                            layout_main.setBackgroundColor(Color.rgb(255, 0, 255))
                        } else {
                            layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                        }
                    }


                }
            }
            timer.start()
        }

        // look up countdowntimer api and see what is needed to implement your
        //own custom timer
        //how to start how to stop how to do things when done
        //how to do things while counting down
        //make anonymous inner class using countdowntimer next class stuff

    }
}

//data type to another data type is called casting i dunno he sounds like pixels rn