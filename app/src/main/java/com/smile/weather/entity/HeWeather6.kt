package com.smile.weather.entity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import com.smile.weather.utils.IconUtils

data class HeWeather6(
    val basic: Basic,
    val daily_forecast: List<DailyForecast>,
    val status: String,
    val update: Update,
    @SerializedName("now")
    val now: Now,

    @SerializedName("air_now_station")
    val airNowStation: List<Air_Now_Station>,
    @SerializedName("air_now_city")
    val airNowCity: Air_Now_City,
    @SerializedName("hourly")
    val hourlyList: List<Hourly>,
    @SerializedName("lifestyle")
    val lifestyleList: List<Lifestyle>

)