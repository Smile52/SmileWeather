package com.smile.weather.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.smile.baselib.utils.ToastUtil

/**
 * 权限申请工具类
 */
object PermissionUtils {
    private const val RESULT_CODE_LOCATION = 1024

    private var locationCallback: (() -> Unit)? = null
    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)


    fun location(context: Context, locationCallback: () -> Unit) {
        this.locationCallback = locationCallback
        permission1(
            context,
            permissions,
            RESULT_CODE_LOCATION,
            locationCallback
        )
    }




    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED

        when (requestCode) {
            RESULT_CODE_LOCATION -> {
                if (cameraAccepted) {
                    locationCallback?.let { it() }
                } else {
                    ToastUtil.showMessage("请开启定位权限")
                }
            }
        }
    }

    private fun permission(
        context: Context,
        systemCode: String,
        resultCode: Int,
        callback: () -> Unit
    ) {
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(
                context,
                systemCode
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            callback()
        } else {
            //申请权限
            ActivityCompat.requestPermissions(context as Activity, arrayOf(systemCode), resultCode)
        }
    }

    /**
     * 权限组申请
     */
    private fun permission1(
        context: Context,
        permissions: Array<String>,
        resultCode: Int,
        callback: () -> Unit
    ) {
        val mPermissionList = ArrayList<String>()
        mPermissionList.clear()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                mPermissionList.add(permission)
            }
        }
        if (mPermissionList.isNotEmpty()) {
            ActivityCompat.requestPermissions(context as Activity, permissions, resultCode)

        }else{
            callback()
        }


    }


}