package com.smile.weather.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * 这里添加了一个外键，主要是为了删除城市的时候会把对应的天气信息删除掉
 */

@Entity(tableName = "CityWeather",foreignKeys =
[(ForeignKey(entity = City::class,parentColumns = ["id"],childColumns =["id"],onDelete = ForeignKey.CASCADE ))])
data class CityWeather(@PrimaryKey @ColumnInfo(name = "id") var id: Int?,
                       @ColumnInfo(name = "now_weather")var nowWeather:String?,
                       @ColumnInfo(name = "one_day_w") var oneDay:String?)