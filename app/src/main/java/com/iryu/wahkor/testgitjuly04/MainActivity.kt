package com.iryu.wahkor.testgitjuly04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class item(val Name:String,var first:Int,var last:Int ,val price:Int,var Active:Boolean)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fooditem:MutableList<item>
        fooditem=ArrayList<item>()
        fooditem.add(0,item("mama",30,20,15,true))
        fooditem.add(fooditem.size,item("snack" ,24,13,5 ,true))
        fooditem.add(fooditem.size,item("jubjai",24,16,10,true))
        fooditem.add(fooditem.size,item("oishi" ,16,15,12,true))
        fooditem.add(fooditem.size,item("water" ,30,17,5 ,true))
        fooditem.add(fooditem.size,item("est"   ,72,30,10,true))
    println("fooditem size =${fooditem.size}")
       var myadape=foodadape(this,fooditem)
        showview.layoutManager=LinearLayoutManager(this)
        showview.adapter=myadape
    }
}
