package com.smile.weather.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.smile.baselib.utils.L
import com.smile.weather.R
import com.smile.weather.utils.DisplayUtils
import kotlin.math.abs

/**
 * 最高温最低温
 */
class TempView :View{

    private val mTempTextColor=ContextCompat.getColor(context, R.color.white)
    private val mTempTextSize=DisplayUtils.dp2px(context, 14f)
    private val mColumnTextMargin=DisplayUtils.dp2px(context, 15f)
    private val mColumnTopMargin=DisplayUtils.dp2px(context,5f)
    private val mColumnWidth=DisplayUtils.dp2px(context,5f)
    private val mColumnBottomMargin=DisplayUtils.dp2px(context,30f)
    private var baseColumnHeight=0f

    private var mMaxArray=0
    private var mMinArray=0
    private var mMaxValue=0
    private var mMinValue=0
    private var mViewHeight=0

    private var mTempTextHeight=0f


    private var mIsDraw=false//是否绘制


    private var mTempIntervalArray=0//数组温度差（7天最高低温度差）

    private var mIsShowBottomTemp=true//是否显示底部温度



    constructor(mContext: Context) : this(mContext, null)

    constructor(mContext: Context, attrs: AttributeSet?) : this(mContext, attrs!!, 0)

    constructor(mContext: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        mContext,
        attrs,
        defStyleAttr
    ) {
        init(mContext, attrs)
    }
    private fun init(mContext: Context, attrs: AttributeSet){

    }

     fun setData(maxArray:Int,minArray:Int ,max:Int,min:Int){
        mMaxArray=maxArray
        mMinArray=minArray
        mMaxValue=max
        mMinValue=min

        mTempIntervalArray=mMaxArray-mMinArray
        mTempIntervalArray+=1

        mIsDraw=true
        invalidate()

    }

    /**
     * 是否为底部显示温度的
     */
    fun setData(maxArray:Int,minArray:Int ,max:Int,min:Int, isShowBottomTemp:Boolean){
        mMaxArray=maxArray
        mMinArray=minArray
        mMaxValue=max
        mMinValue=min

        mTempIntervalArray=mMaxArray-mMinArray
        mTempIntervalArray+=1
        mIsShowBottomTemp=isShowBottomTemp
        mIsDraw=true
        invalidate()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mViewHeight=measuredHeight
       // Log.e("dandy","hh "+mViewHeight  +" ds "+DisplayUtils.dp2px(context,120f))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!mIsDraw){
            return
        }

        var paint= Paint()
        paint.color = mTempTextColor



        paint.style= Paint.Style.FILL
        paint.isAntiAlias=true
        paint.textSize= mTempTextSize.toFloat()
        paint.strokeWidth=5f

        val fontMetrics = Paint.FontMetrics()
        paint.getFontMetrics(fontMetrics)
        val offset = (fontMetrics.descent + fontMetrics.ascent) / 2 // 文字偏移量



        mTempTextHeight= abs(fontMetrics.top)
        // mTempTextHeight=fontMetrics.bottom-fontMetrics.top
        // Log.e("dandy","top "+fontMetrics.top+"  bottom "+fontMetrics.bottom)

        baseColumnHeight = if(mIsShowBottomTemp){
            mViewHeight-(mTempTextHeight*2)-mColumnTopMargin-mColumnBottomMargin//柱状基础高度

        }else{
            mViewHeight-(mTempTextHeight*2)-mColumnTopMargin//柱状基础高度

        }
        val tempPro=(baseColumnHeight/(mTempIntervalArray)).toFloat()//每一度对应的高度
        // Log.e("dandy", "tempPro $tempPro")

        // Log.e("dandy","offset "+offset)

        var topValue=mMaxArray-mMaxValue

        var allValue=mMaxValue-mMinValue

        allValue+=1
        var localValue=mMaxValue-mMinArray
        localValue+=1

        var x=width/2-mColumnWidth/2 //柱子的x坐标

        //((baseColumnHeight-height-(topValue*tempPro))+mTempTextHeight).toFloat()

        var  rectTop=(baseColumnHeight-(localValue*tempPro))+mTempTextHeight+mColumnTopMargin
        var rect= RectF(x.toFloat(),
            rectTop,(x+mColumnWidth).toFloat(),(rectTop+(allValue*tempPro)))



        canvas?.drawRoundRect(rect,30f,30f,paint)


        val maxText= "${mMaxValue}°"
        val minText= "${mMinValue}°"

        var x1 = width / 2 - paint.measureText(maxText) / 2

        canvas?.drawText(maxText,x1,(rect.top+offset),paint)



        if (mIsShowBottomTemp){
            var x2 = width / 2 - paint.measureText(minText) / 2
            canvas?.drawText(minText,x2,rect.bottom+mTempTextHeight, paint)

        }
        
    }
}