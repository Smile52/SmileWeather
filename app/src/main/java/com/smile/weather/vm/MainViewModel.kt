package com.smile.weather.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smile.weather.entity.RefreshEntity
import com.smile.weather.entity.WeatherEntity
import com.smile.weather.repository.MainRepository

class MainViewModel : ViewModel() {
    private var mainRepository: MainRepository = MainRepository()
    private lateinit var mLiveData:MutableLiveData<WeatherEntity>
    private var mRefreshLiveData=MutableLiveData<Int>()


    fun getData(map: Map<String, String>){
        this.mLiveData=mainRepository.login(map)
    }

    fun getDataForLiveData():LiveData<WeatherEntity>{
        return mLiveData
    }

    fun getRefreshLiveData():LiveData<Int>{
        return mRefreshLiveData
    }

     fun setRefresh(entity:Int){
        mRefreshLiveData.value=entity
    }


}