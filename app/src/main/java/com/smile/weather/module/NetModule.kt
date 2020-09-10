package com.smile.weather.module

import android.util.Log
import com.smile.baselib.liveData.LiveDataCallAdapterFactory
import com.smile.weather.BuildConfig
import com.smile.weather.intent.Api
import com.smile.weather.intent.ApiService
import com.smile.weather.intent.ApiService2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * 网络相关的module 对外提供retrofit
 * 目前项目中的API还没有迁移完，所以需要提供不同版本的retrofit
 */
@Module
@InstallIn(ApplicationComponent::class)
object NetModule {

    @Provides
    @Singleton
    fun providerOkHttpClient():OkHttpClient{
        val builder=OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            val loggingInterceptor= HttpLoggingInterceptor()
            loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(loggingInterceptor)
        }

        builder.addInterceptor(initLogInterceptor())
        return OkHttpClient.Builder().build()
    }



    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Api.CITY_BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Named("retrofit2")
    @Provides
    @Singleton
    fun providerRetrofit2(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL_1)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    /**
     *提供城市的retrofit
     */
    @Named("cityRetrofit")
    @Provides
    @Singleton
    fun providerCityRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Api.CITY_BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }



    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun providerApiService2(@Named("retrofit2") retrofit:  Retrofit):ApiService2{
        return retrofit.create(ApiService2::class.java)
    }

    @Named("city")
    @Provides
    @Singleton
    fun providerApiService2ByCity(@Named("cityRetrofit")retrofit: Retrofit):ApiService2{
        return retrofit.create(ApiService2::class.java)
    }



    // 初始化日志
    private fun initLogInterceptor(): Interceptor {

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {message ->  Log.i("dandy",message) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }
}