package com.smile.weather.entity

import com.google.gson.annotations.SerializedName

data class CityEntity(  @SerializedName("HeWeather6")  val HeWeather6: List<HeWeatherCity6>
)