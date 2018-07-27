package com.iryu.wahkor.testgitjuly04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView



class refillAdaper(context: Context, private var itemlist: MutableList<foodrefill>) : BaseAdapter() {

    private var context: Context?= context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.refillsum,parent,false)
        val vh=ViewHolder(view)
        vh.date.text=itemlist[position].time
        if (position>0){
            if (itemlist[position].time==itemlist[position-1].time) {
                vh.date.text = ""
                vh.date.textSize = 0f
            }
        }
        vh.refillsumname.text=itemlist[position].name
        vh.refillsumvalume.text=itemlist[position].value.toString()

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
        var date:TextView
        var refillsumname: TextView
        var refillsumvalume: TextView
        init{
            this.date=view?.findViewById(R.id.date) as TextView
            this.refillsumname=view?.findViewById(R.id.refillsum_name) as TextView
            this.refillsumvalume=view?.findViewById(R.id.refillsum_valume) as TextView


        }
    }
}


