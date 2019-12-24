package com.smile.weather.intent

import com.smile.weather.entity.CityEntity
import com.smile.weather.entity.WeatherEntity
import io.reactivex.Flowable

class ApiManager{

    companion object{
        fun loadForecast(map: Map<String, String>):Flowable<WeatherEntity>{
            return RetrofitClient.retrofit().loadForecastData(map)
        }

        fun loadNow(map: Map<String, String>):Flowable<WeatherEntity>{
            return RetrofitClient.retrofit().loadNowData(map)
        }

        fun loadAirNow(map: Map<String, String>):Flowable<WeatherEntity>{
            return  RetrofitClient.retrofit().loadAirData(map)
        }

        fun loadHourly(map: Map<String, String>):Flowable<WeatherEntity>{
            return RetrofitClient.retrofit().loadHourlyData(map)
        }

        fun searchCity(map: Map<String, String>):Flowable<CityEntity>{
            return RetrofitClient.retrofitForCity().searchCity(map)
        }

        fun topCities(map: Map<String, String>):Flowable<CityEntity>{
            return RetrofitClient.retrofitForCity().topCities(map)
        }
    }
}