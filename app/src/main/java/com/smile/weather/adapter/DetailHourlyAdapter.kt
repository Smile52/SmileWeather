package com.smile.weather.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.smile.baselib.utils.L
import com.smile.weather.R
import com.smile.weather.databinding.ItemHourlyLayoutBinding
import com.smile.weather.entity.Hourly

class DetailHourlyAdapter(var context:Context,var dataList:List<Hourly>): RecyclerView.Adapter<DetailHourlyAdapter.HourlyHolder>() {


    lateinit var mTempArray:IntArray
    var mMaxTemp=0
    var mMinTemp=0



    fun setData(dataList: List<Hourly>){
        this.dataList=dataList
          mTempArray= IntArray(dataList.size)
        for ((i ,hourly) in dataList.withIndex()){
            mTempArray[i]=hourly.tmp.toInt()
        }
        mMaxTemp= mTempArray.max()!!
        mMinTemp=mTempArray.min()!!

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.item_hourly_layout, parent ,false)
        val w: Int = parent.width
        view.layoutParams.width=w/5
        return HourlyHolder(DataBindingUtil.bind<ItemHourlyLayoutBinding>(view)!!)
    }

    override fun getItemCount(): Int {
       return  dataList.size
    }

    override fun onBindViewHolder(holder: HourlyHolder, position: Int) {
        holder.bindIng.itemHourlyTimeTv.text=getTime(dataList[position].time)
        holder.bindIng.hourly=dataList[position]
        holder.bindIng.itemHourlyTempView.setData(mMaxTemp,mMinTemp,dataList[position].tmp.toInt(), mMinTemp,false)

        if (position==0){
            holder.bindIng.itemHourlyWindTv.visibility=View.VISIBLE
        }else{
            holder.bindIng.itemHourlyWindTv.visibility=View.INVISIBLE

        }
    }


    inner class HourlyHolder( val bindIng:ItemHourlyLayoutBinding) : RecyclerView.ViewHolder(bindIng.root) {

    }

   private fun getTime(time:String):String{
        var index=time.indexOf(":")


        return time.substring(index-2,time.length)
    }

}