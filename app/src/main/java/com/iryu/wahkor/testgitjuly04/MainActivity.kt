package com.iryu.wahkor.testgitjuly04

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.text.method.Touch.onTouchEvent
import android.view.GestureDetector
import android.widget.Toast





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
        showview.addOnItemTouchListener(RecyclerTouchListener(this,
                showview, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(this@MainActivity, "Single Click on position        :$position",
                        Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(view: View, position: Int) {
                Toast.makeText(this@MainActivity, "Long press on position :$position",
                        Toast.LENGTH_LONG).show()
            }
        }))
    }

    internal inner class RecyclerTouchListener(context: Context, recycleView: RecyclerView, private val clicklistener: ClickListener?) : RecyclerView.OnItemTouchListener {
        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recycleView.findChildViewUnder(e.x, e.y)
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child))
            }

            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }
}