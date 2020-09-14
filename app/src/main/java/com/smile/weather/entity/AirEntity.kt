package com.smile.weather.entity

/**
 * 空气质量实体类
 */
data class AirEntity(
    val aqi: String,
    val category: String,
    val co: String,
    val no2: String,
    val o3: String,
    val pm10: String,
    val pm2p5: String,
    val primary: String,
    val pubTime: String,
    val so2: String
)