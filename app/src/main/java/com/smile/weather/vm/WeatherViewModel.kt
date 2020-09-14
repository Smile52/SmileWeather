package com.smile.weather.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smile.baselib.entity.BaseResult
import com.smile.weather.base.BaseViewModel
import com.smile.weather.entity.AirEntity
import com.smile.weather.entity.DailyEntity
import com.smile.weather.entity.NowEntity
import com.smile.weather.repository2.WeatherRepository
import javax.inject.Inject

class WeatherViewModel @ViewModelInject constructor(private val weatherRepository: WeatherRepository) :BaseViewModel(){



    fun getWeatherNowInfo(id:String):LiveData<BaseResult<NowEntity>>{
       return weatherRepository.getNowWeatherInfo(getParams(id))

    }

    fun getFutureWeatherList(id:String):LiveData<BaseResult<List<DailyEntity>>>{
        val map=getParams(id)
        return weatherRepository.getFutureWeatherList(map)
    }

    fun getAirNowInfo(id:String):LiveData<BaseResult<AirEntity>>{
        return weatherRepository.getAirNowInfo(getParams(id))
    }




}