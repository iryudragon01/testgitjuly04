package com.iryu.wahkor.testgitjuly04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


data class AddRefillName(var name:String, var value:Int)

class addrefilladapter(context: Context, private var itemlist: MutableList<AddRefillName>) : BaseAdapter() {

    private var context: Context?= context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.addrefill,parent,false)
        val vh=ViewHolder(view)
        vh.name.text=itemlist[position].name
        vh.value.text=itemlist[position].value.toString()

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
        var value: TextView
        init{
            this.name=view?.findViewById(R.id.name) as TextView
            this.value=view?.findViewById(R.id.value) as TextView

        }
    }
}

