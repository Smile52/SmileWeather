package com.smile.weather.ir

import androidx.lifecycle.MutableLiveData
import com.smile.baselib.entity.BaseResult
import com.smile.weather.entity.Now

interface IWeatherRepository {
    fun getCityNow(map: Map<String, String>): MutableLiveData<BaseResult<Now>>
}