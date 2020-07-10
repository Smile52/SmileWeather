package com.smile.weather.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "City")
data class City(
    @PrimaryKey(autoGenerate = true)
    var id:Int?,
    @ColumnInfo(name = "name")
    var name:String?,
    var town:String?,
    var isLocal:Int?,
    var updateTime:String?,

    var cityId:String?



)