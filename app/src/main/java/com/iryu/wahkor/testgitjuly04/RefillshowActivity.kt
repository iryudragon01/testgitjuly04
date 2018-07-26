package com.iryu.wahkor.testgitjuly04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.support.v4.widget.TextViewCompat
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_refillshow.*

class RefillshowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refillshow)
/*
            val mytext = TextView(this)
            mytext.text = "hello iryudragon="
            println("mytext=" + mytext.textSize)
            mytext.textSize = 50f
            refillmain.addView(mytext)
        */
        var textView= TextView(this)
        textView.text="water"
        textView.id=3310

        var textView1= TextView(this)
        textView1.text="almonds"

        var constraintset= ConstraintSet()
        constraintset.clone(refillmain)
        constraintset.connect(textView.id,ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,30)
        /*
        //left to left of
        constraintset.connect(textView.id,ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0)
        //baseline
        constraintset.connect(textView.id,ConstraintSet.BASELINE,textView1.id,ConstraintSet.BASELINE,0)
        //right to right of
        constraintset.connect(textView1.id,ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0)
        //top to top of
        constraintset.connect(textView1.id,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,0)
*/
        constraintset.applyTo(refillmain)

       refillmain.addView(textView)
       //refillmain.addView(textView1)
    }
}
