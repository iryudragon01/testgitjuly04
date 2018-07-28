package com.iryu.wahkor.testgitjuly04

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_refillshow.*

lateinit var addrefilname:MutableList<AddRefillName>
class RefillshowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refillshow)
       // addElementRefilldetail()
        addrefill()
    }
    fun addElementRefilldetail(){
        var refillshow=refillitem
        refillshow.add(refillshow.size,foodrefill("now","name",0))
        val adapter=refillAdaper( this,refillshow)
        stock_showlist.adapter=adapter
    }
    fun addrefill(){
            addrefilname= ArrayList<AddRefillName>()
            for (i in 1 until foodname.size){
                addrefilname.add(AddRefillName(foodname[i],0))
            }


        val adap=addrefilladapter(this, addrefilname)
        stock_showlist.adapter=adap
        stock_showlist.onItemClickListener= AdapterView.OnItemClickListener{ view, _, position, _ ->
            inputmanager= InputManager("refilladd",position, addrefilname[position].name,addrefilname[position].value.toString())
            startActivity(Intent(this,InputActivity::class.java))
        }
    }
}
