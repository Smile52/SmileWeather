package com.smile.weather.repository2

import androidx.lifecycle.LiveData
import com.smile.baselib.entity.BaseResult
import com.smile.weather.db.City
import com.smile.weather.db.CityDao
import com.smile.weather.entity.Location
import com.smile.weather.intent.ApiService
import com.smile.weather.ir.Repository
import javax.inject.Inject


class LocationRepository @Inject constructor(private val cityDao: CityDao, private var apiService: ApiService):Repository {


    fun insertCity(city: City){
        cityDao.insertCity(city)
    }

    fun getCityInfo(map: Map<String, String>):LiveData<BaseResult<List<Location>>>{
        return apiService.getCityInfo(map)
    }


}