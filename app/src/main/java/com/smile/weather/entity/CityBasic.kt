package com.smile.weather.entity

data class CityBasic(
    val admin_area: String,
    val cid: String,
    val cnty: String,
    val lat: String,
    val location: String,
    val lon: String,
    val parent_city: String,
    val type: String,
    val tz: String
)