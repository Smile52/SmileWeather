package com.smile.baselib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smile.baselib.utils.AppManager

abstract class BaseActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)

    }


    open fun initView() {}
    open fun initData() {}


    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this)
    }
}