package com.smile.weather

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.smile.baselib.utils.ToastUtil

class SmileApplication :Application(){

    companion object{
        var instance:SmileApplication?=null

        fun getContext():Context{
            return instance!!
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance=this
        ToastUtil.init(this)
        MultiDex.install(this)

    }
}