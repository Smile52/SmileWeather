package com.smile.weather.base

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.smile.weather.R
import rx.subscriptions.CompositeSubscription
import java.lang.reflect.Field


open abstract class BaseActivity :AppCompatActivity(){
    val mCompositeSubscription: CompositeSubscription = CompositeSubscription()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            var  window= window
           window.clearFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.WHITE
            window.navigationBarColor = ContextCompat.getColor(this, R.color.color_main_bg)

        }


       // makeStatusBarTransparent(this)

        //this.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }

    @SuppressLint("ObsoleteSdkInt")
    open fun makeStatusBarTransparent(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return
        }
        val window: Window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val option: Int = window.decorView
                .systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

    }

    override fun onDestroy() {
        if (mCompositeSubscription.hasSubscriptions()) {
            //取消注册，以避免内存泄露
            mCompositeSubscription.unsubscribe()
        }
        super.onDestroy()
    }

 /*   open fun <M> addSubscription(observable: io.reactivex.Observable<M>, subscriber: Subscriber<M>) {
        mCompositeSubscription.add(
            observable.subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(subscriber))
    }*/

    abstract fun initView()
    open fun initData(){

    }
}