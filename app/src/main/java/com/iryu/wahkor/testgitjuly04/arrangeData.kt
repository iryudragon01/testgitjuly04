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
               ticket.add(ticketitem(tkname[i], tkfirst[i].toInt(),tkfirst[i].toInt(),tklast[i].toInt(), tklast[i].toInt(), tkprice[i].toInt(), singleclick))
              if(i==1){
                  when(j){
                   1 -> elementtype.ticket=tkname.size-1
                   2 -> elementtype.airpay=tkname.size-1
                   3 -> elementtype.food=tkname.size-1
               }}
           }

       }
       println("tickket count="+elementtype.ticket)
       println("airpay count="+elementtype.airpay)
       println("food count="+elementtype.food)
       refilladd(allitem[4])
       statementmanagement(allitem[5],allitem[6])
   }

fun refilladd(refill:String) {

    addrefilname= ArrayList<AddRefillName>()
    refillitem=ArrayList<foodrefill>()
    val food=refill.split("<||>")
    if (food[1]!="norefill"){
        val allfood=food[1].split("<sum>")
        val itemfood=allfood[0].split("<&&>")
        sumallfood=allfood[1].split(",")
        for (i in 0 until itemfood.size){
            val subfood=itemfood[i].split(",")
            for (j in 1 until subfood.size){
                if(subfood[j].toInt() >0){
                    refillitem.add(foodrefill(subfood[0],foodname[j],subfood[j].toInt()))


            }}
        }
        for(i in 1 until foodname.size){
             ticket[foodstart+i-1].first+=sumallfood[i-1].toInt()
        }

    }
    firstairpay=ArrayList<FirstAirpay>()
    for (i in elementtype.ticket until elementtype.ticket+ elementtype.airpay) {
        firstairpay.add(FirstAirpay(ticket[i].first,ticket[i].last))
        ticket[i].last -= ticket[i].first
        ticket[i].first=0
        ticket[i].first_old=0
        ticket[i].last_old= ticket[i].last_old
    }

}

fun statementmanagement(income: String, expense: String) {
    Income=ArrayList<Statement>()
    Expense=ArrayList<Statement>()
    addIncome=ArrayList<Statement>()
    addExpense=ArrayList<Statement>()
    if(income.split("<||>")[1]!=""){
        Income=extractExpense(income.split("<||>")[1])
    }
    if(expense.split("<||>")[1]!=""){
        Expense=extractExpense(expense.split("<||>")[1])
    }
}

fun extractExpense(rawdata: String): MutableList<Statement> {
    var data: MutableList<Statement>
    data=ArrayList<Statement>()
    val splitdata=rawdata.split("<&&>")
    for (i in 0 until splitdata.size){
        val donedata=splitdata[i].split(",")
        data.add(Statement(donedata[0],donedata[1],donedata[2].toInt()))
    }
    return data

}

data class ticketitem(val name:String,var first:Int,var first_old:Int,var last:Int,var last_old:Int,val price:Int,val singleclick:Int)
lateinit var ticket:MutableList<ticketitem>
data class foodrefill(val time:String,val name:String,val value:Int)
data class Statement(val date:String,val name:String,val value:Int)
data class Elementtype(var ticket:Int,var airpay:Int,var food:Int)
data class FirstAirpay(var airstart:Int,var airlast:Int)
var elementtype=Elementtype(0,0,0)
lateinit var Income:MutableList<Statement>
lateinit var Expense:MutableList<Statement>
lateinit var refillitem:MutableList<foodrefill>
lateinit var foodname:List<String>
lateinit var sumallfood:List<String>
var foodstart=0
lateinit var firstairpay:MutableList<FirstAirpay>