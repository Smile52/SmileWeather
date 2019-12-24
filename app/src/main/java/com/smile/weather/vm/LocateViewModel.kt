package com.smile.weather.vm

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.smile.weather.db.AppDataBase
import com.smile.weather.db.City
import com.smile.weather.db.CityDao
import com.smile.weather.db.CityWeatherDao
import com.smile.weather.entity.*
import com.smile.weather.repository.LocateRepository

class LocateViewModel :ViewModel(){


    private val mDao: CityDao by lazy {
        AppDataBase.instance.getCityDao()
    }

    private val mCityWeatherDao: CityWeatherDao by lazy {
        AppDataBase.instance.getCityWeatherDao()
    }

    private val mRepository by lazy {
        LocateRepository()
    }





    private lateinit var mSearchLiveData: MutableLiveData<CityEntity>

    private lateinit var mDaoLiveData:LiveData<List<City>>
    private lateinit var mLocateLiveData:LiveData<List<LocateEntity>>


    fun searchCity(map: Map<String, String>){
        mSearchLiveData=mRepository.searchCity(map)
    }

    fun getSearchLiveData():LiveData<CityEntity>{
        return mSearchLiveData
    }


    fun getCityList():LiveData<List<City>>{
        return mDao.getAll()
    }

    /**
     * 获取city列表并且转换成我们需要的List<LocateEntity>
     */
    fun getWeatherList():LiveData<List<LocateEntity>>{
        mLocateLiveData=Transformations.map(mDao.getAll()){
            list->

            transform(list)
        }

        return mLocateLiveData
    }

    private fun transform(list: List<City>):List<LocateEntity>{
        var locateList= ArrayList<LocateEntity>()
        for (city in list){
            var now:Now?=null
            var oneDay:DailyForecast?=null
            val weather=mCityWeatherDao.getWeatherById(city.id!!)

            if (weather.nowWeather!=null&&!TextUtils.isEmpty(weather.nowWeather)){
               now =Gson().fromJson(weather.nowWeather, Now::class.javaObjectType)

            }
            if (weather.oneDay!=null&&!TextUtils.isEmpty(weather.oneDay)){
                oneDay=Gson().fromJson(weather.oneDay, DailyForecast::class.javaObjectType)

            }

            locateList.add(
                LocateEntity(city,
                    select = false,
                    open = false,
                    now = now,
                    oneDay = oneDay
                )
            )
        }
        return locateList

    }






}