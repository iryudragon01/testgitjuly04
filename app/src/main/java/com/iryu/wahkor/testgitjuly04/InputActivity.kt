package com.iryu.wahkor.testgitjuly04

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_input.*
var editposition=-1
data class InputManager(var type:String,var position:Int,var name:String,var oldvalue:String)
lateinit var inputmanager:InputManager
class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        button0.setOnClickListener{onbuttonclick("0")}
        button01.setOnClickListener{onbuttonclick("1")}
        button2.setOnClickListener{onbuttonclick("2")}
        button3.setOnClickListener{onbuttonclick("3")}
        button4.setOnClickListener{onbuttonclick("4")}
        button5.setOnClickListener{onbuttonclick("5")}
        button6.setOnClickListener{onbuttonclick("6")}
        button7.setOnClickListener{onbuttonclick("7")}
        button8.setOnClickListener{onbuttonclick("8")}
        button9.setOnClickListener{onbuttonclick("9")}
        buttonplus.setOnClickListener{onbuttonclick("+")}
        buttonminus.setOnClickListener{onbuttonclick("-")}
        buttonmulti.setOnClickListener{onbuttonclick("*")}
        buttondiv.setOnClickListener{onbuttonclick("/")}
        buttonback.setOnClickListener{onbuttonclick("del")}
        clear.setOnClickListener{onbuttonclick("clear")}
        buttonenter.setOnClickListener{onbuttonclick("enter")}
        inputview.hint= inputmanager.name+" = "+ inputmanager.oldvalue
    }

    private fun onbuttonclick(button: String) {
        when(button){
            "clear" -> inputview.text=""
            "del" -> {
                val substr=inputview.text.toString()
                if( substr.length>0 ) {
                    inputview.text = substr.substring(0, substr.length - 1)
                }
            }
            "enter" -> {
                try {
                    ticket[editposition].last=evalstring.eval(inputview.text.toString()).toInt()

                    startActivity(Intent(this,MainActivity::class.java))
                }catch (e:Throwable){
                    Toast.makeText(this, "Input Format don't correct",Toast.LENGTH_SHORT).show()}


            }
           else -> inputview.text=inputview.text.toString()+button
        }

    }
}
