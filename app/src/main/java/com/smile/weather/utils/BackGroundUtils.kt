package com.smile.weather.utils

import android.util.Log
import com.smile.baselib.utils.L
import com.smile.weather.R

/**
 * 获取背景的工具类
 */
object BackGroundUtils{

    fun getBackGroundByCode(code:Int):Int{

        Log.e("dandy1","code $code")

        if (code==100){
            return R.drawable.sun1
        }
        if (code in 101..104){
            return R.drawable.cloud1
        }
        if (code in 200..299){
            return R.drawable.cloudy1
        }
        if (code==300){
            return R.drawable.light_rain1
        }
        if (code in 301..304){
            return R.drawable.thunder1
        }
        if (code==399){
            return R.drawable.rain1

        }
        if (code in 305..318){
            return R.drawable.rain1
        }
        if (code in 400..499){
            return R.drawable.snow1
        }


        return R.drawable.cloud1
    }

}