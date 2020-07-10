package com.smile.weather.entity



data class City(
    val location: List<Location>
)

data class Location(
    val adm1: String,
    val adm2: String,
    val country: String,
    val fxLink: String,
    val id: String,
    val isDst: String,
    val lat: String,
    val lon: String,
    val name: String,
    val rank: String,
    val type: String,
    val tz: String,
    val utcOffset: String
)

data class Refer(
    val license: List<String>,
    val sources: List<String>
)
