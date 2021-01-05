package com.smile.weather.entity

/**
 * 日出日落，月相实体类
 */
data class SumMoonEntity(
    val fxDate: String,
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    val tempMax: String,
    val tempMin: String,
    val iconDay: String,
    val textDay: String,
    val iconNight: String,
    val textNight: String,
    val wind360Day: String,
    val windDirDay: String,
    val windScaleDay: String,
    val windSpeedDay: String,
    val wind360Night: String,
    val windDirNight: String,
    val windScaleNight: String,
    val windSpeedNight: String,
    val humidity: String,
    val precip: String,
    val pressure: String,
    val vis: String,
    val uvIndex: String
)
