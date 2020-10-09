package com.smile.weather.module

import android.util.Log
import com.smile.baselib.liveData.LiveDataCallAdapterFactory
import com.smile.baselib.utils.L
import com.smile.weather.intent.Api
import com.smile.weather.intent.ApiService2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
    fun providerOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(initLogInterceptor())
        return builder.build()
    }

    @Provides
    @Singleton
    fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
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
    fun providerRetrofit2(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL_2)
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
    fun providerCityRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Api.CITY_BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }


  /*  @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
*/

    @Provides
    @Singleton
    fun providerApiService2(@Named("retrofit2") retrofit: Retrofit): ApiService2 {
        return retrofit.create(ApiService2::class.java)
    }

    @Named("city")
    @Provides
    @Singleton
    fun providerApiService2ByCity(@Named("cityRetrofit") retrofit: Retrofit): ApiService2 {
        return retrofit.create(ApiService2::class.java)
    }


    /**
     * 初始化日志输出
     */
    private fun initLogInterceptor(): Interceptor {

        val loggingInterceptor = HttpLoggingInterceptor { message -> L.i( message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }
}