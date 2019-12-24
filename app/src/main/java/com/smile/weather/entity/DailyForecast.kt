package com.smile.weather.entity

data class DailyForecast(
    val cond_code_d: String,
    val cond_code_n: String,
    val cond_txt_d: String,
    val cond_txt_n: String,
    val date: String,
    val hum: String,
    val mr: String,
    val ms: String,
    val pcpn: String,
    val pop: String,
    val pres: String,
    val sr: String,
    val ss: String,
    val tmp_max: String,
    val tmp_min: String,
    val uv_index: String,
    val vis: String,
    val wind_deg: String,
    val wind_dir: String,
    val wind_sc: String,
    val wind_spd: String
)