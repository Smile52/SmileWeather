package com.smile.weather.entity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.smile.weather.utils.IconUtils

/**
 * 实况天气
 */
data class Now(
    val cloud: String,
    val cond_code: String,
    val cond_txt: String,
    val fl: String,
    val hum: String,
    val pcpn: String,
    val pres: String,
    val tmp: String,
    val vis: String,
    val wind_deg: String,
    val wind_dir: String,
    val wind_sc: String,
    val wind_spd: String

)

