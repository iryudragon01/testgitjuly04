package com.iryu.wahkor.testgitjuly04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_refillshow.*

class RefillshowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refillshow)
addElementRefilldetail()
    }
    fun addElementRefilldetail(){
        println(refillitem.size)
        for (i in 0 until refillitem.size){
            println(refillitem[i].time+" "+ refillitem[i].name+" ="+ refillitem[i].value)
        }
        val adapter=refillAdaper( this,refillitem)
        stock_showlist.adapter=adapter
    }
}
