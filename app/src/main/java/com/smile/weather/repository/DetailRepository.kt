package com.smile.weather.repository

import androidx.lifecycle.MutableLiveData
import com.smile.baselib.base.BaseRepository
import com.smile.weather.entity.WeatherEntity
import com.smile.weather.intent.ApiManager
import com.smile.weather.ir.IDetailRepository
import com.smile.weather.ir.IMainRepository

class DetailRepository : BaseRepository<WeatherEntity>(), IDetailRepository {


    override fun loadNow(map: Map<String, String>): MutableLiveData<WeatherEntity> {

        return request(ApiManager.loadNow(map),"loadNow").send()!!.get()
    }

    override fun loadAirNow(map: Map<String, String>): MutableLiveData<WeatherEntity> {
        return  request(ApiManager.loadAirNow(map),"loadAirNow").send()!!.get()
    }

    override fun loadHourly(map: Map<String, String>): MutableLiveData<WeatherEntity> {
        return request(ApiManager.loadHourly(map),"loadHourly").send()!!.get()
    }

    override fun loadForecast(map: Map<String, String>): MutableLiveData<WeatherEntity> {
        return request(ApiManager.loadForecast(map),"loadForecast").send()!!.get()
    }


}