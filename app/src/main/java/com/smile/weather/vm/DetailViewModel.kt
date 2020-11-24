package com.smile.weather.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {


    val refreshing: MutableLiveData<Boolean> = MutableLiveData()


}