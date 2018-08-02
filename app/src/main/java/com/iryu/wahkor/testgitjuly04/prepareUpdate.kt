package com.iryu.wahkor.testgitjuly04

class prepareUpdate{
    fun convertstatementdata(rawdata:MutableList<Statement>):String{
        var data=""
        for (i in 0 until rawdata.size){
            if (data!=""){data+="<<__>>"}
            data+=rawdata[i].date+","+rawdata[i].name+","+rawdata[i].value.toString()
        }
        println(data)
        return data
    }
    fun convertticketdata(start:Int,end:Int,airpay:Boolean):String{

        var data="date"
        var unchange=true
        for (i in start until end){
           val lastdata= if (airpay)ticket[i].last+ firstairpay[i- elementtype.ticket].airstart else ticket[i].last
            data+=","+lastdata
            if (ticket[i].last!= ticket[i].last_old || ticket[i].first != ticket[i].first_old){
                unchange=false}
        }
        if (unchange) return "" else
       return data
    }
    fun convertaddrefill(rawdata:MutableList<AddRefillName>):String{
        if (rawdata.size==0 ){return ""}
        var total=0
        var data="date"
        for (i in 0 until rawdata.size){
            data+=","+rawdata[i].value
            total+=rawdata[i].value
        }
        if (total==0)return "" else
        return data
    }

    fun update():String{
        var url="action=senddata"
        val stock=convertticketdata(0, elementtype.ticket,false)
        val airpay=convertticketdata(elementtype.ticket, elementtype.airpay+elementtype.ticket,true)
        val fstock=convertticketdata(elementtype.airpay+elementtype.ticket,elementtype.airpay+elementtype.ticket+ elementtype.food,false)
        val addincome=convertstatementdata(addIncome)
        val addexpense=convertstatementdata(addExpense)
        val newfood=convertaddrefill(addrefilname)
        val del_income=""
        val del_expense=""
        println("stock=${stock}\nairpay=${airpay}\nfstock=${fstock}")
        url+="&return_stockdata="+stock
        url+="&airpaystart=${ticket[elementtype.ticket].first}"
        url+="&return_airpay="+airpay
        url+="&return_fstockdata="+fstock
        url+="&GoogleId=${User.id}"
        url+="&tempincome=${addincome}&tempexpense=${addexpense}"
        url+="&return_refill=${newfood}"

        url+="&del_income=${del_income}&del_expense=${del_expense}"
        println("addrefill=${newfood}")
return url
        /*
        url+="&del_income=${if (checkdelitem(del_income))"" else del_income}&del_expense=${if (checkdelitem(del_expense))"" else del_expense}"

        GoogleScript().execute(url)
        newfood="";loadfstock =true;loadstock =true;tempincome="";tempexpense="";del_expense="";del_income=""
    */
    }

}