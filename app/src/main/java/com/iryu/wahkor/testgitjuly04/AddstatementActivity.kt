package com.iryu.wahkor.testgitjuly04

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_addstatement.*
lateinit var addIncome:MutableList<Statement>
lateinit var addExpense:MutableList<Statement>

class AddstatementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstatement)
        incomecheck.isChecked= !expensecheck.isChecked
        expensecheck.setOnClickListener{incomecheck.isChecked= !expensecheck.isChecked}
        incomecheck.setOnClickListener{expensecheck.isChecked= !incomecheck.isChecked}
        okbt.setOnClickListener{setuptemp()}
        cancelbt.setOnClickListener { name.setText("");valume.setText("") }
        cancelbt.text="Clear"

    }
    private fun callparent(){
        startActivity(Intent(this,StatementActivity::class.java))
    }
    private fun setuptemp() {
        if (name.text.toString()!="" && valume.text.toString()!="" && valume.text.toString()!="0"){}else{return}
        if (incomecheck.isChecked){
            addIncome.add(Statement("now",name.text.toString(),valume.text.toString().toInt()))

        }else{
            addExpense.add(Statement("now",name.text.toString(),valume.text.toString().toInt()))
        }
        name.setText("");valume.setText("")
        callparent()
    }

    override fun onBackPressed() {
        callparent()
    }
}