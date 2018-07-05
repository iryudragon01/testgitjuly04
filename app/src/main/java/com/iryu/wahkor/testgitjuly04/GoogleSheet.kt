package com.iryu.wahkor.testgitjuly04

import android.os.AsyncTask
import java.net.HttpURLConnection
import java.net.URL

open class GoogleSheet:AsyncTask<String,String,String>(){
    override fun doInBackground(vararg params: String?): String {
        var url = "https://script.google.com/macros/s/AKfycbwH6fLr3mO1-KcreVb5HcNoI_wATVveJs7RmOayBpYq/dev?"

        for (i in 0 until params.size) {
            url += params[i]
        }
        //println(url)
        val result: String
        val connection = URL(url).openConnection() as HttpURLConnection


        try {
            connection.connect()
            result = connection.inputStream.bufferedReader().readText()
        } finally {
            connection.disconnect()
        }

        return result
    }

}