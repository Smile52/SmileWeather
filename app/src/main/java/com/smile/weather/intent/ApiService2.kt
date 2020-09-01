package com.smile.weather.intent

import androidx.lifecycle.LiveData
import com.smile.baselib.entity.BaseResult
import com.smile.weather.entity.NowEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService2 {

    @GET("weather/now")
    fun getNoWData(@QueryMap map: Map<String, String>): LiveData<BaseResult<NowEntity>>

}