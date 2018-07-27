package com.iryu.wahkor.testgitjuly04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_refillshow.*

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
        val adap=addrefilladapter(this, foodname)
        stock_showlist.adapter=adap
    }
}
