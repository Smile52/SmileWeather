package com.smile.weather.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.smile.weather.R
import com.smile.weather.entity.CityBasic
import com.smile.weather.entity.Location
import java.lang.StringBuilder

class SearchAdapter(data:List<Location>):BaseQuickAdapter<Location, BaseViewHolder>(R.layout.item_search_layout, data){
    override fun convert(helper: BaseViewHolder, item: Location?) {
        helper.setText(R.id.item_search_city_name_tv, item?.name)



       // helper.setText(R.id.item_search_province_tv, builder.toString())
    }

}