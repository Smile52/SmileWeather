package com.smile.weather.entity

/**
 * 逐小时预报
 */
data class Hourly(
    val cloud: String,
    val cond_code: String,
    val cond_txt: String,
    val dew: String,
    val hum: String,
    val pop: String,
    val pres: String,
    val time: String,
    val tmp: String,
    val wind_deg: String,
    val wind_dir: String,
    val wind_sc: String,
    val wind_spd: String
)