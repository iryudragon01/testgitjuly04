package com.iryu.wahkor.testgitjuly04

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_refillshow.*

lateinit var addrefilname:MutableList<AddRefillName>
class RefillshowActivity : AppCompatActivity() {
    var clickable=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refillshow)
        backmain.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
        refill.setOnClickListener { addElementRefilldetail()}
        addrefill.setOnClickListener { addrefill() }
       // addElementRefilldetail()
        addElementRefilldetail()
    }
    fun addElementRefilldetail(){
        clickable=false
        var refillshow=refillitem
        val adapter=refillAdaper( this,refillshow)
        stock_showlist.adapter=adapter
    }
    fun addrefill(){
        clickable=true
        if(addrefilname.size==0){
            for (i in 1 until foodname.size){
                addrefilname.add(AddRefillName(foodname[i],0))
            }}


        val adap=addrefilladapter(this, addrefilname)
        stock_showlist.adapter=adap
        stock_showlist.onItemClickListener= AdapterView.OnItemClickListener{ view, _, position, _ ->
            if (clickable){
            inputmanager= InputManager("refilladd",position, addrefilname[position].name,addrefilname[position].value.toString())
            returnIntent=Intent(this,RefillshowActivity::class.java)
            startActivity(Intent(this,InputActivity::class.java))}
        }
    }
}
