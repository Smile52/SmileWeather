package com.smile.weather.ir

import androidx.lifecycle.MutableLiveData
import com.smile.weather.entity.WeatherEntity

interface IDetailRepository {


    fun loadNow(map: Map<String, String>): MutableLiveData<WeatherEntity>

    fun loadAirNow(map: Map<String, String>) :MutableLiveData<WeatherEntity>

    fun loadHourly(map: Map<String, String>):MutableLiveData<WeatherEntity>

    fun loadForecast(map: Map<String, String>):MutableLiveData<WeatherEntity>

}