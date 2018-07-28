package com.iryu.wahkor.testgitjuly04


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.widget.AdapterView
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_statement.*

var del_income=""
var del_expense=""
class StatementActivity : AppCompatActivity() {
    private var itemnum=0
   // private var statementadape=ArrayList<stockModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)
      //  datestart.setText(startdateaccount)
        menubt02.text="รายรับ"
        menubt03.text="รายจ่าย"
        menubt04.text="เพิ่ม รายการ"
        menubt01.text="หน้าหลัก"
        menubt01.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
        menubt02.setOnClickListener {}
        menubt03.setOnClickListener {}
        //menubt04.setOnClickListener { startActivity(Intent(this,AddStatementActivity::class.java)) }
      //  menubt01.setBackgroundColor(unselectBTcolor)
        addStatement()
    }

    private fun addStatement() {
        var statement:MutableList<Statement>
        statement=ArrayList<Statement>()
        if(Income.size>0){
            for (i in 0 until Income.size){
                statement.add(Statement(Income[i].date, Income[i].name, Income[i].value))
            }
        }
        if(Expense.size>0){
            for (i in 0 until Expense.size){
                statement.add(Statement(Expense[i].date, Expense[i].name, Expense[i].value))
            }
        }
        val myadape= statementadapter(this, statement)
        stock_showlist.adapter=myadape
        stock_showlist.onItemClickListener=AdapterView.OnItemClickListener{ _,_,position,_ ->
           // myAlert(this,position)
        }
    }
    /*

    fun del_itemmanager(position: Int,oldtext:String):String{
        val editincome=  (position < IncomeData.split("<&&>").size)
        val realposition=if (editincome)position else position- IncomeData.split("<&&>").size
        val data=if (editincome)del_income.split(",") else del_expense.split(",")
        val edata=data.toTypedArray()
        val editval = if (edata[realposition]=="0") "1" else "0"
        val rdata=oldtext.split("_de")
        var tempdata=""
        for (i in 0 until  edata.size){
            tempdata+=if (i==0)(if (realposition==i)editval else edata[i])
            else ","+(if (i==realposition)editval else edata[i])
        }
        if (editincome) del_income=tempdata else del_expense=tempdata
        return if(rdata.size==2) rdata[0] else rdata[0]+"_del"
    }

    fun myAlert(context: Context, position:Int) {
        if (itemnum>position){
            statementadape[position].stockstart=del_itemmanager(position,statementadape[position].stockstart)
            val myadape= stock_adapter(this, statementadape, 20f, 35f, 35f)
            stock_showlist.adapter=myadape
            return}
        val alert= AlertDialog.Builder(this)
        var edittextlast: EditText?=null
        with(alert){
            setTitle(statementadape[position].stockstart)
            setMessage("ยอดล่าสุด")

            edittextlast= EditText(context)
            edittextlast!!.hint= statementadape[position].stocklast
            edittextlast!!.inputType = InputType.TYPE_CLASS_NUMBER
            setPositiveButton("OK") { dialog, _ -> dialog.dismiss()
                val last=edittextlast!!.text.toString()
                statementadape[position].stocklast=last
                stock_showlist.adapter= stock_adapter(context, statementadape, 20F, 35F, 35F)
                tempincome=""
                tempexpense=""
                for(i in 0 until statementadape.size){
                    if(statementadape[i].stockname=="expense") {
                        if (tempexpense!=""){  tempexpense+=","   }
                        tempexpense+=statementadape[i].stockstart+","+statementadape[i].stocklast

                    }
                    if(statementadape[i].stockname=="income") {
                        if (tempincome!=""){  tempincome+=","   }
                        tempincome+=statementadape[i].stockstart+","+statementadape[i].stocklast

                    }
                }}

            setNegativeButton("NO") {
                dialog, _ ->
                dialog.dismiss()
            }
        }

        // Dialog
        val dialog = alert.create()
        dialog.setView(edittextlast)
        dialog.show()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }*/
}
