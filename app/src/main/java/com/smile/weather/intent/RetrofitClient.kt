package com.smile.weather.intent

import android.os.Build
import android.util.Log
import com.smile.baselib.liveData.LiveDataCallAdapterFactory
import com.smile.weather.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    fun retrofit():ApiService{
        val builder=OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            val loggingInterceptor=HttpLoggingInterceptor()
            loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        builder.addInterceptor(initLogInterceptor())
        val okHttpClient = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun retrofitForCity():ApiService{
        val builder=OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            val loggingInterceptor=HttpLoggingInterceptor()
            loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
            // builder.addInterceptor(loggingInterceptor)

        }
        builder.addInterceptor(initLogInterceptor())
        val okHttpClient = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Api.CITY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun retrofitForGetCityInfo():ApiService{
        val builder=OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            val loggingInterceptor=HttpLoggingInterceptor()
            loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
            // builder.addInterceptor(loggingInterceptor)

        }
        builder.addInterceptor(initLogInterceptor())
        val okHttpClient = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Api.CITY_BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())

            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    // 初始化日志
    private fun initLogInterceptor(): Interceptor {

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {message ->  Log.i("dandy",message) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }
}