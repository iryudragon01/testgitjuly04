package com.iryu.wahkor.testgitjuly04

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.GestureDetector

lateinit var context: Context
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this
    actionbt.setOnClickListener { startActivity(Intent(this,RefillshowActivity::class.java))}
        if (editposition==-1){
        GoogleScript().execute("action=getdataall&GoogleId=${User.id}")}
        else{
            showitem(editposition)
        }
    }
    fun showitem(scroll:Int){

        var myadape=foodadape(this,ticket)
        showview.layoutManager= LinearLayoutManager(this)
        showview.adapter=myadape
        showview.scrollToPosition(scroll)
        showview.addOnItemTouchListener(RecyclerTouchListener(this,
                showview, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                //Values are passing to activity & to fragment as well

                ticket[position].last+= ticket[position].singleclick
                var myadape=foodadape(view.context,ticket)
                showview.adapter=myadape
                showview.scrollToPosition(position)
            }

            override fun onLongClick(view: View, position: Int) {
                myAlert(view,position)

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
   inner   class GoogleScript():GoogleSheet(){
        override fun onPostExecute(result: String?) {

                println(result)
            val unwrap=(result as String).split("<||>")
            if (unwrap[0]=="getdataall") {
                ticketadd(result)
                editposition=1
                startActivity(callmain)
            }
            return
        }
          }


    fun myAlert(view:View, position:Int) {
        editposition=position
        inputmanager= InputManager("editTicket",position,ticket[position].name,ticket[position].last.toString())
        returnIntent=Intent(this,MainActivity::class.java)
        val intent= Intent(this,InputActivity::class.java)
        startActivity(intent)

    }
}
data class ticketitem(val name:String,var first:Int,var last:Int,val price:Int,val singleclick:Int)
lateinit var ticket:MutableList<ticketitem>

