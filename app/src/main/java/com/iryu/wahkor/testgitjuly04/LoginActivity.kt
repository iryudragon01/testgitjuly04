package com.iryu.wahkor.testgitjuly04


import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
data class user(var name:String,var level:Int,var id:String)
 var User=user("",99,"")
lateinit var callmain:Intent
var iscallmian=0
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        startActivity(Intent(this,InputActivity::class.java))
        val submitbt=findViewById<Button>(R.id.summitbt)
        submitbt.setOnClickListener {login();}
        callmain=Intent(this,MainActivity::class.java)
    }

    private fun login() {
        val username=findViewById<EditText>(R.id.userbox)
        val password=findViewById<EditText>(R.id.passbox)
        val suburl="callfrom=android&action=login&user=${username.text.toString()}&password=${password.text.toString()}"
        GoogleScript().execute(suburl)
    }
     inner class GoogleScript:GoogleSheet(){
        override fun onPostExecute(result: String?) {
            val unwarp=(result as String).split("<||>")
            if (unwarp[0]=="login") {
                val parm = unwarp[1].split(",")
                User.name= parm[0]
                User.level= parm[1].toInt()
                User.id = parm[2]
                println("name=${User.name}\nlevel=${User.level}\nid=${User.id}")
                 startActivity(callmain)
            }else{
                Toast.makeText(this@LoginActivity,result,Toast.LENGTH_LONG).show()
            }
        }
    }//end inner  class

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBackPressed() {
        //finish()
        finishAffinity()
    }
}
