package com.smile.weather.exception

import java.lang.Exception

class ApiException(code: Int, msg: String) : Exception() {
    private val code:Int = code
    private val msg:String = msg


}