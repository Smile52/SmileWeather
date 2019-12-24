package com.smile.weather.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import retrofit2.http.DELETE

@Dao
interface CityDao {


    @Query("SELECT * FROM City ")
    fun getAll():LiveData<List<City>>

    @Query("SELECT * FROM City WHERE id=1")
    fun getLocalCity():City

    @Query("SELECT * FROM City WHERE id=:i")
    fun getCityById(i:Int): City

    @Insert(onConflict = REPLACE)
    fun insertCity(city: City)

    @Insert(onConflict = REPLACE)
    fun updateCity(city: City)

    @Delete
    fun deleteCity(city: City)

    @Query("SELECT * FROM City ORDER BY id DESC LIMIT 1")
    fun getLastCity():City


}