package com.smile.weather.entity

import com.google.gson.annotations.SerializedName

data class HeWeatherCity6( @SerializedName("basic") val basics:List<CityBasic> )