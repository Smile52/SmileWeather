package com.smile.weather.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object SwipeRefreshLayoutHandler {

    @JvmStatic
    @BindingAdapter("bind_swipeRefreshLayout_refreshing")
    fun setSwipeRefreshLayoutRefreshing(
        swipeRefreshLayout: SwipeRefreshLayout,
        newValue: Boolean
    ) {
        if (swipeRefreshLayout.isRefreshing != newValue)
            swipeRefreshLayout.isRefreshing = newValue
    }

    @JvmStatic
    @InverseBindingAdapter(
        attribute = "bind_swipeRefreshLayout_refreshing",
        event = "bind_swipeRefreshLayout_refreshingAttrChanged"
    )
    fun isSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout): Boolean =
        swipeRefreshLayout.isRefreshing

    @JvmStatic
    @BindingAdapter(
        "bind_swipeRefreshLayout_refreshingAttrChanged",
        requireAll = false
    )
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