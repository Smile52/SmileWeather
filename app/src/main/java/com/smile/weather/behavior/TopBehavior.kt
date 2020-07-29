package com.smile.weather.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.smile.baselib.utils.L
import com.smile.weather.utils.DisplayUtils
import com.smile.weather.view.BetterGesturesRecyclerView

class TopBehavior constructor(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    private var  fY=0f
    private val  headViewHeight=DisplayUtils.dp2px(context, 200f)


    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0

    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        child.alpha=0f

        return super.onLayoutChild(parent, child, layoutDirection)
    }




    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )


/*
        fY += dyConsumed


       // L.e("Y $fY  height ${target.height} ")

        fY=if (fY<=0) 0f else fY

        if (fY<=headViewHeight){
            var  a=1+(fY-headViewHeight)/headViewHeight
            a=if (a>1f)1f else a
            child.alpha=a
        }else{
            child.alpha=1f
        }*/
    }


    override fun onNestedFling(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    /**
     *     确实是否要监听这次滑动
     */
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        directTargetChild: View,
        target: View,
        axes: Int
    ): Boolean {
        return true
    }

    /**
     * 父view的滑动调用
     */
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)

        fY += dy


        fY=if (fY<=0) 0f else fY

        L.e("fy $fY")
        if (fY<=headViewHeight){
            var  a=1+(fY-headViewHeight)/headViewHeight
            a=if (a>1f)1f else a
            child.alpha=a
        }else{
            child.alpha=1f
        }
    }




    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        type: Int
    ) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)

    }



}