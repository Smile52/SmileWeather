package com.smile.weather.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


/**
 * 里面的recyclerview滚动时 不让viewpager滚动
 */
class SRecyclerView :RecyclerView {

    private var mTouchSlop = 0


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
                if (abs(y - move_y) > mTouchSlop && abs(x - move_x) < mTouchSlop * 2) {
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



}

