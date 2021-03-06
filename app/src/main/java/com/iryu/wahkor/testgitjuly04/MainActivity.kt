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
var LoadData=true
lateinit var context: Context
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this
        statement.setOnClickListener { startActivity(Intent(this,StatementActivity::class.java))}
        refill.setOnClickListener { startActivity(Intent(this,RefillshowActivity::class.java))}
        sing.setOnClickListener { startActivity(Intent(this,LoginActivity::class.java))}
        update.setOnClickListener {

            activatebutton(false)
           val url=prepareUpdate().update()
            GoogleScript().execute(url)
        }
        if (LoadData){
            activatebutton(false)
        GoogleScript().execute("action=getdataall&GoogleId=${User.id}")}
        else{
            showitem(editposition)
        }
    }
    fun activatebutton(boolean:Boolean){
        statement.isClickable=boolean
        refill.isClickable=boolean
        sing.isClickable=boolean
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
      activatebutton(true)
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
                editposition=0
                LoadData=false
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

