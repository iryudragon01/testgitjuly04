package com.iryu.wahkor.testgitjuly04

   fun ticketadd(result:String){
       val allitem=result.split("|||")
       ticket=ArrayList<ticketitem>()
       for(j in 1 until 4){
           val tkitem=allitem[j].split("<||>")[1].split("<&&>")
           val tkname=tkitem[0].split(",")
           val tkprice=tkitem[1].split(",")
           val tkfirst=tkitem[2].split(",")
           val tklast=tkitem[3].split(",")
           if(j==3){
               foodname=tkname
               foodstart=ticket.size
           }
           for(i in 1 until tkname.size) {
               var singleclick = 0
               when (j) {
                   1 -> singleclick = 1
                   2 -> singleclick = 10
                   3 -> singleclick = -1
               }
               ticket.add(ticketitem(tkname[i], tkfirst[i].toInt(), tklast[i].toInt(), tkprice[i].toInt(), singleclick))
           }

       }
       refilladd(allitem[4],allitem[5],allitem[6])
   }

fun refilladd(refill:String,income: String, expense: String) {
    val food=refill.split("<||>")
    if (food[1]!="norefill"){
        val allfood=food[1].split("<sum>")
        val itemfood=allfood[0].split("<&&>")
        sumallfood=allfood[1].split(",")
        for (i in 0 until itemfood.size){
            val subfood=itemfood[i].split(",")
            refillitem=ArrayList<foodrefill>()
            for (j in 1 until subfood.size){
                if(subfood[j].toInt() >0){
                refillitem.add(foodrefill(subfood[0],foodname[j],subfood[j].toInt()))

            }}
        }
        for(i in 1 until foodname.size){
             ticket[foodstart+i-1].first+=sumallfood[i-1].toInt()
        }
    }

}

data class foodrefill(val time:String,val name:String,val value:Int)
lateinit var refillitem:MutableList<foodrefill>
lateinit var foodname:List<String>
lateinit var sumallfood:List<String>
var foodstart=0