package com.iryu.wahkor.testgitjuly04

class prepareUpdate{
    fun convertstatementdata(rawdata:MutableList<Statement>):String{
        var data=""
        for (i in 0 until rawdata.size){
            if (data!=""){data+="<<__>>"}
            data=rawdata[i].date+rawdata[i].name+","+rawdata[i].value.toString()
        }
        return data
    }
    fun convertticketdata(start:Int,end:Int):String{
        var data=""

        for (i in start until end){
            if (data != ""){data+=","}
            data+=ticket[i].last.toString()

        }
       return data
    }
    fun convertaddrefill(rawdata:MutableList<AddRefillName>):String{
        var data=""
        for (i in 0 until rawdata.size){
            if (data!=""){data+=","}
            data+=rawdata[i].value
        }

        return data
    }

    fun update(){
        var url="action=senddata"
        val stock=convertticketdata(0, elementtype.ticket)
        val airpay=convertticketdata(elementtype.ticket, elementtype.airpay+elementtype.ticket)
        val fstock=convertticketdata(elementtype.airpay+elementtype.ticket,elementtype.airpay+elementtype.ticket+ elementtype.food)
        val addincome=convertstatementdata(addIncome)
        val addexpense=convertstatementdata(addExpense)
        val newfood=convertaddrefill(addrefilname)
        url+="&return_stockdata="+stock
        url+="&airpaystart=${ticket[elementtype.ticket].first}"
        url+="&return_airpay="+airpay
        url+="&return_fstockdata="+fstock
        url+="&GoogleId=${User.id}"
        url+="&tempincome=${addincome}&tempexpense=${addexpense}"
        url+="&return_refill=${newfood}"

        /*
        url+="&del_income=${if (checkdelitem(del_income))"" else del_income}&del_expense=${if (checkdelitem(del_expense))"" else del_expense}"

        GoogleScript().execute(url)
        newfood="";loadfstock =true;loadstock =true;tempincome="";tempexpense="";del_expense="";del_income=""
    */
    }

}