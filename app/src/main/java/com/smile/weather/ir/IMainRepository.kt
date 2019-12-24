package com.smile.weather.ir

import androidx.lifecycle.MutableLiveData
import com.smile.weather.entity.WeatherEntity

interface IMainRepository {


    fun login(map: Map<String, String>): MutableLiveData<WeatherEntity>

}