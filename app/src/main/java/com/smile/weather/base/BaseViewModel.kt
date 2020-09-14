package com.smile.weather.base

import androidx.lifecycle.ViewModel
import com.smile.weather.config.Config

open class BaseViewModel :ViewModel(){


    open fun getParams(location:String):Map<String, String>{


        return mutableMapOf("location" to location,"key" to Config.API_KEY)

    }


}