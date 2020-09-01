package com.smile.weather.intent

import androidx.lifecycle.LiveData
import com.smile.baselib.entity.BaseResult
import com.smile.weather.entity.*
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*

interface  ApiService{

    @GET("weather/forecast")
    fun loadForecastData(@QueryMap map:Map<String,String>) : Flowable<WeatherEntity>

    @GET("air/now")
    fun loadAirData(@QueryMap map: Map<String, String>) : Flowable<WeatherEntity>

    @GET("weather/now")
    fun loadNowData(@QueryMap map: Map<String, String>):Flowable<WeatherEntity>



    @GET("weather/hourly")
    fun loadHourlyData(@QueryMap map: Map<String, String>):Flowable<WeatherEntity>

    @GET("weather/lifestyle")
    fun loadLifeStyle(@QueryMap map: Map<String, String>):Flowable<WeatherEntity>

    @GET("find")
    fun searchCity(@QueryMap map: Map<String, String>):Flowable<CityEntity>

    @GET("top")
    fun topCities(@QueryMap map: Map<String, String>):Flowable<CityEntity>


    //======新版接口

    /**
     * 获取城市信息
     */
    @GET("city/lookup")
    fun getCityInfo(@QueryMap map: Map<String, String>): LiveData<BaseResult<List<Location>>>

/*
    @GET("v7/weather/now")
    fun getNoWData(@QueryMap map: Map<String, String>):LiveData<BaseResult<NowEntity>>*/




}