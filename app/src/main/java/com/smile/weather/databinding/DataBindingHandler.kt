package com.smile.weather.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.smile.weather.R
import com.smile.weather.utils.IconUtils


/**
 * 踩坑  由于kotlin没有static的特性和null-safe检查   添加 @JvmStatic 和参数后面加？
 */
object DataBindingHandler {

    @BindingAdapter("loadByCode")
    @JvmStatic
    fun loadImageByCode(imageView: ImageView, code: String?) {
        if (code != null) {
            imageView.setImageResource(IconUtils.getSmallIcon(code.toInt()!!))
        }
    }

    @BindingAdapter("airQuality")
    @JvmStatic
    fun airQuality(textView: TextView, type: String?) {
        when (type) {
            "优", "良" -> {
                textView.setBackgroundResource(R.drawable.shape_air_quality_1_bg)
            }

            "轻度污染" -> {
                textView.setBackgroundResource(R.drawable.shape_air_quality_2_bg)
            }

            "中度污染", "重度污染" -> {
                textView.setBackgroundResource(R.drawable.shape_air_quality_3_bg)
            }
        }
    }

    @BindingAdapter("windIcon")
    @JvmStatic
    fun windIcon(imageView: ImageView, content: String?) {
        if (content != null) {
            imageView.setImageResource(IconUtils.getWindIcon(content))

        }
    }

}