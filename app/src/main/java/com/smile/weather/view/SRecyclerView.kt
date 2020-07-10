package com.smile.weather.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.smile.baselib.utils.L
import java.lang.Math.abs


/**
 * 里面的recyclerview滚动时 不让viewpager滚动
 */
class SRecyclerView :RecyclerView {

    private var mTouchSlop = 100

    private var mTouchOffset=30 //触摸偏移量


    constructor(mContext: Context) : this(mContext, null)

    constructor(mContext: Context, attrs: AttributeSet?) : this(mContext, attrs!!, 0)

    constructor(mContext: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        mContext,
        attrs,
        defStyleAttr
    ) {
        init(mContext, attrs)
    }

    private fun init(mContext: Context, attrs: AttributeSet) {
        val vc=ViewConfiguration.get(context)
        mTouchSlop=vc.scaledPagingTouchSlop

    }

   private var move_x = 0
   private var move_y = 0

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        when (e!!.action) {
            MotionEvent.ACTION_DOWN -> {
                move_x = e.x.toInt()
                move_y = e.y.toInt()
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val y = e.y.toInt()
                val x = e.x.toInt()
                if (kotlin.math.abs(y - move_y) > mTouchSlop && kotlin.math.abs(x - move_x) < mTouchSlop * 2) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return super.onTouchEvent(e)
    }

    /**
     * 一直拦截水平滑动，避免滑动到最后一个item的时候带动viewpager2滑动
     */
    override fun canScrollHorizontally(direction: Int): Boolean {
        return true
    }

    /**
     *   不让垂直滑动， 滑动事件交给外层recycleViews处理
     */
    override fun canScrollVertically(direction: Int): Boolean {
        return false
    }


}

