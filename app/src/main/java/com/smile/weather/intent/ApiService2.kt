package com.smile.weather.intent

import androidx.lifecycle.LiveData
import com.smile.baselib.entity.BaseResult
import com.smile.weather.entity.Location
import com.smile.weather.entity.NowEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService2 {

    @GET("weather/now")
    fun getNoWData(@QueryMap map: Map<String, String>): LiveData<BaseResult<NowEntity>>


    /**
     * 搜索城市
     */
    @GET("city/lookup")
    fun searchCity(@QueryMap map: Map<String, String>):LiveData<BaseResult<List<Location>>>


    /**
     * 获取城市信息
     */
    @GET("city/lookup")
    fun getCityInfo(@QueryMap map: Map<String, String>): LiveData<BaseResult<List<Location>>>

}