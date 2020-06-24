package com.smile.weather.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.smile.weather.config.Config

open class BaseFragment :Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {

            if (Build.VERSION.SDK_INT>= 21) {
                val decorView: View = activity!!.window.decorView
                decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                activity!!.window.statusBarColor = Color.TRANSPARENT;


            }

        super.onCreate(savedInstanceState)
    }

    open fun getParams(name:String):Map<String, String>{


        return mutableMapOf("location" to name,"key" to Config.API_KEY)

    }
}