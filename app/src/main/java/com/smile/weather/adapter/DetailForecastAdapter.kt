package com.smile.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.smile.weather.R
import com.smile.weather.databinding.ItemForecastLayoutBinding
import com.smile.weather.databinding.ItemHourlyLayoutBinding
import com.smile.weather.entity.DailyForecast
import com.smile.weather.utils.DateUtils

class DetailForecastAdapter(val context:Context,private var dataList:List<DailyForecast>) : RecyclerView.Adapter<DetailForecastAdapter.ForecastHolder>() {

    private var mMaxAll=0
    private var mMinAll=0



    fun setData(data: List<DailyForecast>){
        dataList=data

        var maxArray=IntArray(dataList.size)
        var minArray=IntArray(dataList.size)


        for ((i, forecast) in dataList.withIndex()){
            maxArray[i]=forecast.tmp_max.toInt()
            minArray[i]=forecast.tmp_min.toInt()

        }
        mMaxAll= maxArray.max()!!
        mMinAll=minArray.min()!!



        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHolder {
        var view= LayoutInflater.from(context).inflate(R.layout.item_forecast_layout, parent ,false)
        val w: Int = parent.width
        view.layoutParams.width=w/5
        return ForecastHolder(DataBindingUtil.bind(view)!!)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {
        val  entity=dataList[position]
        holder.bindIng.dailyForecast=entity
        holder.bindIng.itemFTempView.setData(mMaxAll, mMinAll,entity.tmp_max.toInt(),entity.tmp_min.toInt() )

        if (position==0){
            holder.bindIng.itemFTimeTv.text="今天"
        }else{
            holder.bindIng.itemFTimeTv.text=DateUtils.getTime(DateUtils.dateToStamp(entity.date).toString())
        }

    }

    inner class ForecastHolder(var bindIng:ItemForecastLayoutBinding):RecyclerView.ViewHolder(bindIng.root)

}

