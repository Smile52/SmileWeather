package com.smile.weather.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smile.weather.entity.WeatherEntity
import com.smile.weather.repository.DetailRepository
import com.smile.weather.repository.MainRepository

class DetailViewModel : ViewModel() {
    private var detailRepository: DetailRepository = DetailRepository()
    private lateinit var mLiveData: MutableLiveData<WeatherEntity>
    private lateinit var mAirLiveData: MutableLiveData<WeatherEntity>
    private lateinit var mHourlyLiveData: MutableLiveData<WeatherEntity>

    private lateinit var mForecastLiveData: MutableLiveData<WeatherEntity>

    private lateinit var mLifeStyleLiveData: MutableLiveData<WeatherEntity>

    val refreshing: MutableLiveData<Boolean> = MutableLiveData()


    fun getNowData(map: Map<String, String>) {
        this.mLiveData = detailRepository.loadNow(map)
    }

    fun getNowDataForLiveData(): LiveData<WeatherEntity> {
        return mLiveData
    }

    fun getAirData(map: Map<String, String>) {
        mAirLiveData = detailRepository.loadAirNow(map)
    }

    fun getAirDataForLiveData(): LiveData<WeatherEntity> {
        return mAirLiveData
    }

    fun getHourlyData(map: Map<String, String>) {
        mHourlyLiveData = detailRepository.loadHourly(map)
    }

    fun getHourlyForLiveData(): LiveData<WeatherEntity> {
        return mHourlyLiveData
    }

    fun getForecastData(map: Map<String, String>) {
        mForecastLiveData = detailRepository.loadForecast(map)
    }

    fun getForecastForeLiveData(): LiveData<WeatherEntity> {
        return mForecastLiveData
    }

    fun getLifeStyleData(map: Map<String, String>) {
        mLifeStyleLiveData = detailRepository.loadLifeStyle(map)
    }

    fun getLifeStyleLiveData(): LiveData<WeatherEntity> {
        return mLifeStyleLiveData
    }
}