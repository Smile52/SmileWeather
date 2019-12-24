package com.smile.weather.repository

import androidx.lifecycle.MutableLiveData
import com.smile.baselib.base.BaseRepository
import com.smile.weather.entity.WeatherEntity
import com.smile.weather.intent.ApiManager
import com.smile.weather.ir.IMainRepository

class MainRepository : BaseRepository<WeatherEntity>(), IMainRepository {


    override fun login(map: Map<String, String>): MutableLiveData<WeatherEntity> {

        return request(ApiManager.loadForecast(map)).send()!!.get()
    }

}