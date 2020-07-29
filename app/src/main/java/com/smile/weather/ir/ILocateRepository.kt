package com.smile.weather.ir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smile.baselib.entity.BaseResult
import com.smile.weather.entity.City
import com.smile.weather.entity.CityEntity
import com.smile.weather.entity.Location

interface ILocateRepository {

    fun searchCity(map: Map<String, String>):MutableLiveData<CityEntity>

    fun topCities(map: Map<String, String>):MutableLiveData<CityEntity>

   // fun getCityInfo(map: Map<String, String>):MutableLiveData<City>

    fun getCityInfo(map: Map<String, String>):LiveData<BaseResult<List<Location>>>
}