package com.smile.baselib.utils

import android.util.Log

class L {
    companion object{
        private const val isDebug=false
        private const val TAG="dandy"
        fun i(msg:String){
            if (isDebug) {
                Log.i(TAG, msg)
            }else{

            }
        }

        fun e(msg:String){
            Log.e(TAG,msg)

        }
    }
}