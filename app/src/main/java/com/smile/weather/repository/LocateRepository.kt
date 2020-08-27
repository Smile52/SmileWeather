package com.smile.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smile.baselib.base.BaseRepository
import com.smile.baselib.entity.BaseResult
import com.smile.weather.entity.City
import com.smile.weather.entity.CityEntity
import com.smile.weather.entity.Location
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

    override fun getCityInfo(map: Map<String, String>): LiveData<BaseResult<List<Location>>> {
        return ApiManager.getCityInfo(map)
    }




    /* override fun getCityInfo(map: Map<String, String>): MutableLiveData<City>{
         return request(ApiManager.getCityInfo(map), "getCityInfo").send()!!.get()
     }*/


}