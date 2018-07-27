package com.iryu.wahkor.testgitjuly04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView




class addrefilladapter(context: Context, private var itemlist: List<String>) : BaseAdapter() {

    private var context: Context?= context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.addrefill,parent,false)
        val vh=ViewHolder(view)
        vh.name.text=itemlist[position]
        vh.value.setText("")

        if (position==0){
            view.visibility=View.GONE
            vh.name.textSize=0f
            vh.value.textSize=0f
        }
        return view
    }


    override fun getItem(position: Int): Any {
        return itemlist[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemlist.size
    }

    private class ViewHolder(view: View?) {
        var name: TextView
        var value: EditText
        init{
            this.name=view?.findViewById(R.id.name) as TextView
            this.value=view?.findViewById(R.id.value) as EditText

        }
    }
}

