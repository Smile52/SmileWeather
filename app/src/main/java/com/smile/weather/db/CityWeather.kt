package com.smile.weather.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "CityWeather",foreignKeys =
[(ForeignKey(entity = City::class,parentColumns = ["id"],childColumns =["id"],onDelete = ForeignKey.CASCADE ))])
data class CityWeather(@PrimaryKey @ColumnInfo(name = "id") var id: Int?,
                       @ColumnInfo(name = "now_weather")var nowWeather:String?,
                       @ColumnInfo(name = "one_day_w") var oneDay:String?)