package com.smile.weather.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smile.weather.entity.WeatherEntity


class MainViewModel : ViewModel() {
    private lateinit var mLiveData:MutableLiveData<WeatherEntity>
    private var mRefreshLiveData=MutableLiveData<Int>()



     fun setRefresh(entity:Int){
        mRefreshLiveData.value=entity
    }


}