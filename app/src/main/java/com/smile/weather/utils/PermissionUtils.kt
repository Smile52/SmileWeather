package com.smile.weather.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.smile.baselib.utils.ToastUtil

object PermissionUtils{
    private const val RESULT_CODE_LOCATION=1024


    private var locationCallback:(()->Unit)?=null

    fun location(context: Context, locationCallback:()->Unit){
        this.locationCallback=locationCallback
        permission(context, Manifest.permission.ACCESS_COARSE_LOCATION, RESULT_CODE_LOCATION, locationCallback)
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED

        when(requestCode){
            RESULT_CODE_LOCATION ->{
                if (cameraAccepted){
                    locationCallback?.let { it() }
                }else{
                    ToastUtil.showMessage("请开启定位权限")
                }
            }
        }
    }
        private fun permission(context: Context, systemCode: String, resultCode: Int, callback: () -> Unit){
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(context, systemCode) == PackageManager.PERMISSION_GRANTED) {
            callback()
        } else {
            //申请权限
            ActivityCompat.requestPermissions(context as Activity, arrayOf(systemCode), resultCode)
        }
    }

}