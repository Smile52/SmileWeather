package com.smile.weather.repository2

import androidx.lifecycle.LiveData
import com.smile.baselib.entity.BaseResult
import com.smile.weather.db.CityWeatherDao
import com.smile.weather.entity.AirEntity
import com.smile.weather.entity.DailyEntity
import com.smile.weather.entity.NowEntity
import com.smile.weather.intent.ApiService
import com.smile.weather.intent.ApiService2
import com.smile.weather.ir.Repository
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherDao: CityWeatherDao,
                    private val apiService: ApiService2
):Repository{


    fun getNowWeatherInfo(map: Map<String, String>):LiveData<BaseResult<NowEntity>>{
        return apiService.getNoWData(map)
    }

    fun getFutureWeatherList(map: Map<String, String>):LiveData<BaseResult<List<DailyEntity>>>{
        return apiService.getFutureWeatherList(map)
    }

    fun getAirNowInfo(map: Map<String, String>):LiveData<BaseResult<AirEntity>>{
        return apiService.getAriNow(map)
    }


}