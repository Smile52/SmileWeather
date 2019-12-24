package com.smile.baselib.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.HttpException


open class BaseHttpSubscriber<T:Any>: FlowableSubscriber<T>{

    private var data: MutableLiveData<T> = MutableLiveData()

    fun get(): MutableLiveData<T> {
        return data
    }


    override fun onNext(t: T) {
        onFinish(t)

        }

    override fun onComplete() {
    }

    override fun onSubscribe(s: Subscription) {
        s!!.request(1)

    }

    override fun onError(t: Throwable?) {

    }



    private fun onFinish(t: T){
        data?.value =t
    }



}