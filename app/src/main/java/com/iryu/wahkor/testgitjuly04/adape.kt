package com.iryu.wahkor.testgitjuly04

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adape.view.*

class foodadape(var context: Context,private var itemlist:MutableList<ticketitem> ):RecyclerView.Adapter<viewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view=LayoutInflater.from(parent?.context).inflate(R.layout.adape,parent,false)

        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder?.view?.name.text=itemlist[position].name
        holder?.view?.first.text=itemlist[position].first.toString()
        holder?.view?.last.text=itemlist[position].last.toString()
          }


}
class viewholder(val view:View):RecyclerView.ViewHolder(view){

}