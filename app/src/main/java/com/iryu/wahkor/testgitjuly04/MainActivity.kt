package com.iryu.wahkor.testgitjuly04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
class item(val Name:String,var Vulume:Int,val Active:Boolean)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mylist:MutableList<String>
        var fooditem:MutableList<item>
        fooditem=ArrayList<item>()
        fooditem.add(0,item("mama",7,true))
        fooditem.add(1,item("snack",24,true))
        fooditem.add(2,item("jubjai",30,true))
        fooditem.add(3,item("oishi",15,true))
        fooditem.add(4,item("water",40,true))
        fooditem.add(fooditem.size,item("est",66,true))
        fooditem.forEach { subfood ->
            println("${ subfood.Name}= ${subfood.Vulume}" )
            subfood.Vulume++
        }
        fooditem.forEach { subfood ->
            println("${ subfood.Name}= ${subfood.Vulume}" )
        }
    }
}
