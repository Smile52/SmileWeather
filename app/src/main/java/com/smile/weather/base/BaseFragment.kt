package com.smile.weather.base

import androidx.fragment.app.Fragment
import com.smile.weather.config.Config

open class BaseFragment :Fragment(){


    open fun getParams(name:String):Map<String, String>{


        return mutableMapOf("location" to name,"key" to Config.API_KEY)

    }
}