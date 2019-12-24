package com.smile.weather.sing

import androidx.lifecycle.MutableLiveData

object SingLiveData {
    private val mCityChange:MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun deleteCity(pos :Int){
        mCityChange.value=pos
    }

    fun getCityChangeLiveData():MutableLiveData<Int>{
        return mCityChange
    }

}