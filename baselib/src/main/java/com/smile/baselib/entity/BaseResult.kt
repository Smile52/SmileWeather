package com.smile.baselib.entity

data class BaseResult<T>(
    var status:String,
    var code: String,
    var updateTime: String,
    var fxLink: String,
    var location: T?,
    var now: T?
)

