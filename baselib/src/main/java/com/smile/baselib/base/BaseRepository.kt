package com.smile.baselib.base

import com.smile.baselib.base.BaseHttpSubscriber
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseRepository<T:Any>{
    private var baseHttpSubscriber: BaseHttpSubscriber<T>?=null
    private var flowable: Flowable<T>? = null
    private var map:HashMap<String, BaseHttpSubscriber<T>> = hashMapOf()

    fun send(): BaseHttpSubscriber<T>? {

        flowable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(baseHttpSubscriber)

        return baseHttpSubscriber
    }

    fun request(flowable: Flowable<T>):BaseRepository<T>{
        baseHttpSubscriber= BaseHttpSubscriber()

        this.flowable=flowable
        return this
    }

    fun request(flowable: Flowable<T>, name:String):BaseRepository<T>{
        if (map?.get(name) ==null){
            baseHttpSubscriber=BaseHttpSubscriber()
            map[name] = baseHttpSubscriber!!
        }else{
            baseHttpSubscriber=map[name]
        }
        this.flowable=flowable

        return this
    }

}