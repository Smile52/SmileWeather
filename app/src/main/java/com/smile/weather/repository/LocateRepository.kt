package com.smile.weather.repository

import androidx.lifecycle.MutableLiveData
import com.smile.baselib.base.BaseRepository
import com.smile.weather.entity.CityEntity
import com.smile.weather.entity.WeatherEntity
import com.smile.weather.intent.ApiManager
import com.smile.weather.ir.ILocateRepository

class LocateRepository : BaseRepository<CityEntity>(),ILocateRepository{
    override fun searchCity(map: Map<String, String>): MutableLiveData<CityEntity> {

        return request(ApiManager.searchCity(map),"searchCity").send()!!.get()
    }

    override fun topCities(map: Map<String, String>): MutableLiveData<CityEntity> {
        return request(ApiManager.topCities(map),"topCities").send()!!.get()
    }

}