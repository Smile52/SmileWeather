package com.smile.baselib.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {

    var mToast: Toast? = null

    fun init(context: Context?) {
        mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
    }

    fun showMessage(message: String?) {
        showMessage(message, Toast.LENGTH_SHORT)
    }

    fun showLongMessage(message: String?) {
        showMessage(message, Toast.LENGTH_LONG)
    }

    fun showMessage(messageId: Int) {
        showMessage(messageId, Toast.LENGTH_SHORT)
    }

    fun showMessage(message: String?, duration: Int) {
        mToast!!.setText(message)
        mToast!!.duration = duration
        mToast!!.show()
    }

    fun showMessage(messageId: Int, duration: Int) {
        mToast!!.setText(messageId)
        mToast!!.duration = duration
        mToast!!.show()
    }
}
