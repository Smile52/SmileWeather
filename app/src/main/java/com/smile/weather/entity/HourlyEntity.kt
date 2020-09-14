package com.smile.weather.entity

/**
 * 未来 小时天气实体类
 */
data class HourlyEntity(
    val cloud: String,
    val dew: String,
    val fxTime: String,
    val humidity: String,
    val icon: String,
    val pop: String,
    val precip: String,
    val pressure: String,
    val temp: String,
    val text: String,
    val wind360: String,
    val windDir: String,
    val windScale: String,
    val windSpeed: String
)