package com.smile.weather.utils

import com.smile.weather.R
import com.smile.weather.SmileApplication
import java.lang.StringBuilder

object IconUtils {
    fun getSmallIcon(code: Int):Int{

        val context = SmileApplication.getContext()
        var builder=StringBuilder("s_")
        builder.append(code)
        val id=context.resources.getIdentifier(builder.toString(),"drawable",context.packageName)

        return if (id==-1) R.drawable.s_unknown else id
    }

    fun getWindIcon(content:String):Int{
        return when(content){
            "北风"-> R.drawable.icon_wind_north
            "东北风"-> R.drawable.icon_wind_east_north
            "东风"-> R.drawable.icon_wind_east
            "东南分"-> R.drawable.icon_wind_east_south
            "南风"-> R.drawable.icon_wind_south
            "西南风"-> R.drawable.icon_wind_west_south
            "西风"-> R.drawable.icon_wind_west
            "西北风" -> R.drawable.icon_wind_west_north
            else
            -> R.drawable.icon_wind_north
        }
    }

}