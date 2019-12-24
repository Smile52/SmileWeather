package com.smile.weather.entity

import com.smile.weather.db.City

data class LocateEntity(var city:City,var select:Boolean, var open:Boolean, var now:Now?,var oneDay:DailyForecast?)