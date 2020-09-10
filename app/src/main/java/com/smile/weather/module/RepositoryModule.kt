package com.smile.weather.module

import com.smile.weather.db.CityDao
import com.smile.weather.intent.ApiService
import com.smile.weather.intent.ApiService2
import com.smile.weather.repository2.LocationRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Singleton


@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {


    @Singleton
    @ActivityRetainedScoped
    fun  providerLocationRepository(cityDao: CityDao, apiService: ApiService2):LocationRepository{
        return LocationRepository(cityDao, apiService)
    }

}