package com.smile.baselib.liveData

import androidx.lifecycle.LiveData
import com.smile.baselib.entity.BaseResult
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory :CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) !=LiveData::class.java)
            return null
        //获取第一个泛型类型的数据
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        //获取泛型的class
        val rawType = getRawType(observableType)
        //判断是类型一致
        if (rawType != BaseResult::class.java) {
            throw IllegalArgumentException("type must be ApiResponse")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
        return LiveDataCallAdapter<Any>(observableType)
    }
}