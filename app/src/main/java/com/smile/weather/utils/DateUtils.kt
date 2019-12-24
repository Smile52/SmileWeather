package com.smile.weather.utils

import com.smile.baselib.utils.L
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
    @Throws(ParseException::class)
    fun dateToStamp(s: String?): String? {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = simpleDateFormat.parse(s)
        val ts: Long = date.time
        res = ts.toString()
        return res
    }

    fun getTime(time:String):String{

        val  c=Calendar.getInstance()
        c.time=Date(time.toLong())

        return when(c.get(Calendar.DAY_OF_WEEK)){
            1-> "周日"
            2-> "周一"
            3-> "周二"
            4-> "周三"
            5-> "周四"
            6-> "周五"
            7-> "周六"
            else -> ""

        }
    }

    fun  getCurrentTime():String{
        val  c=Calendar.getInstance()
        val month=c.get(Calendar.MONTH)+1
        val day=c.get(Calendar.DAY_OF_MONTH)
        val way= getTime(System.currentTimeMillis().toString())

        val hour=c.get(Calendar.HOUR_OF_DAY)
        val minute=c.get(Calendar.MINUTE)

        val builder=StringBuilder()
        builder.append(month)
        builder.append("月")
        builder.append(day)
        builder.append("日,")
        builder.append(way)
        builder.append(",")
        builder.append(hour)
        builder.append(":")
        if (minute<10){
            builder.append("0$minute")

        }else{
            builder.append(minute)

        }
        return builder.toString()
    }

}