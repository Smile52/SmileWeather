package com.smile.weather.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.smile.baselib.utils.L

object SwipeRefreshLayoutHandler {

    @BindingAdapter("app:bind_swipeRefreshLayout_refreshing")
    @JvmStatic
    fun setSwipeRefreshLayoutRefreshing(
        swipeRefreshLayout: SwipeRefreshLayout,
        newValue: Boolean
    ) {
        if (swipeRefreshLayout.isRefreshing != newValue)
            swipeRefreshLayout.isRefreshing = newValue
    }

    @JvmStatic
    @InverseBindingAdapter(
        attribute = "app:bind_swipeRefreshLayout_refreshing",
        event = "app:bind_swipeRefreshLayout_refreshingAttrChanged"
    )
    fun isSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout): Boolean =
        swipeRefreshLayout.isRefreshing

    @BindingAdapter(
        "app:bind_swipeRefreshLayout_refreshingAttrChanged",
        requireAll = false
    )
    @JvmStatic
    fun setOnRefreshListener(
        swipeRefreshLayout: SwipeRefreshLayout,
        bindingListener: InverseBindingListener?
    ) {
        if (bindingListener != null)
            swipeRefreshLayout.setOnRefreshListener {
                bindingListener.onChange()
            }
    }
}