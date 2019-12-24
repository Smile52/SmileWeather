package com.smile.weather.intent

import com.smile.weather.entity.CityEntity
import com.smile.weather.entity.WeatherEntity
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

    @GET("find")
    fun searchCity(@QueryMap map: Map<String, String>):Flowable<CityEntity>

    @GET("top")
    fun topCities(@QueryMap map: Map<String, String>):Flowable<CityEntity>

}