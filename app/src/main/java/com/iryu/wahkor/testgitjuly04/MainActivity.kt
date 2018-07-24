package com.iryu.wahkor.testgitjuly04

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.GestureDetector
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionbt.setOnClickListener { showitem(0) }
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
      class GoogleScript():GoogleSheet(){
        override fun onPostExecute(result: String?) {

                println(result)
            val unwrap=(result as String).split("<||>")
            if (unwrap[0]=="getdataall") {
                extract(result as String)
            }
            return
        }
          fun extract(result:String){
              val allitem=result.split("|||")
              ticket=ArrayList<ticketitem>()
              for(j in 1 until 4){
                  val tkitem=allitem[j].split("<||>")[1].split("<&&>")
                  val tkname=tkitem[0].split(",")
                  val tkprice=tkitem[1].split(",")
                  val tkfirst=tkitem[2].split(",")
                  val tklast=tkitem[3].split(",")
              for(i in 1 until tkname.size){
                  var singleclick=0
                  when (j) {
                      1 -> singleclick=1
                      2 -> singleclick=10
                      3 -> singleclick=-1
                  }
               ticket.add(ticketitem(tkname[i],tkfirst[i].toInt(),tklast[i].toInt(),tkprice[i].toInt(),singleclick))
              }}
              println("show what in ticket")
              ticket.forEach { someitem ->
                  println(someitem.name)
                  println(someitem.singleclick)
              }

          }
    }

    fun myAlert(view:View, position:Int) {
        editposition=position
        val intent= Intent(this,InputActivity::class.java)
        startActivity(intent)

    }
}
data class ticketitem(val name:String,var first:Int,var last:Int,val price:Int,val singleclick:Int)
lateinit var ticket:MutableList<ticketitem>

