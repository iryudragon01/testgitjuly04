package com.iryu.wahkor.testgitjuly04


fun checkeval(data:String):Boolean{
    try {
        evalstring.eval(data)
        return true
    }catch (e:Throwable){return false}
}
fun inputData(inputdata:String, oldvalue:Int, min:Int, max:Int):Int {
    if (evalstring.eval(inputdata)<min ||evalstring.eval(inputdata)>max) {
        println("input out of range")
        return oldvalue
    }else{

        return evalstring.eval(inputdata).toInt()
    }
}

/*
fun setFstockFirstdata(data:String){
    if (data==""){return}
    val first= data_fstock_first.split(",")
    val subdata=data.split(",")
    data_fstock_first=first[0]
    for (i in 1 until first.size){
        data_fstock_first+=","+(first[i].toInt()+evalstring.eval(subdata[i])).toInt().toString()
    }
}
fun diffOldandNew(olddata:String,newdata:String){
    var diffdata=olddata.split(",")[0]
    for (i in 1 until olddata.split(",").size){
        diffdata+=","+(newdata.split(",")[i].toInt()-
                olddata.split(",")[i].toInt()).toString()
    }

    setFstockFirstdata(diffdata)
}
        */