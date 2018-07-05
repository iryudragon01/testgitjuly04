package com.iryu.wahkor.testgitjuly04

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.GestureDetector
import android.widget.Toast





class item(val Name:String,var first:Int,var last:Int ,val price:Int,var Active:Boolean)
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionbt.setOnClickListener { showitem() }
        GoogleScript().execute("action=getdataall&GoogleId=${User.id}")

    }
    fun showitem(){

        var myadape=foodadape(this,ticket)
        showview.layoutManager= LinearLayoutManager(this)
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
      class GoogleScript():GoogleSheet(){
        override fun onPostExecute(result: String?) {

                println(result)
            val unwrap=(result as String).split("<||>")
            if (unwrap[0]=="getdataall") {
                extract(unwrap[2])
            }
            return
        }
          fun extract(result:String){
              val move=result.split("|||")
              val unwrap=move[0].split("<&&>")
              val unname=unwrap[0].split(",")
              val unprice=unwrap[1].split(",")
              val unfirst=unwrap[2].split(",")
              val unlast=unwrap[3].split(",")
              ticket=ArrayList<ticketitem>()
              for(i in 1 until unname.size){
               ticket.add(ticketitem(unname[i],unfirst[i].toInt(),unlast[i].toInt(),unprice[i].toInt()))
              }
              println("show what in ticket")
              ticket.forEach { someitem ->
                  println(someitem.name)
                  println(someitem.first)
                  println(someitem.last)
                  println(someitem.price)
              }

          }
    }

}
data class ticketitem(val name:String,var first:Int,var last:Int,val price:Int)
lateinit var ticket:MutableList<ticketitem>

