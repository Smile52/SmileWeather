package com.smile.weather.ir

import androidx.lifecycle.MutableLiveData
import com.smile.weather.entity.CityEntity

interface ILocateRepository {

    fun searchCity(map: Map<String, String>):MutableLiveData<CityEntity>

    fun topCities(map: Map<String, String>):MutableLiveData<CityEntity>


}