package com.smile.weather.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.smile.weather.R
import com.smile.weather.entity.CityBasic
import java.lang.StringBuilder

class SearchAdapter(data:List<CityBasic>):BaseQuickAdapter<CityBasic, BaseViewHolder>(R.layout.item_search_layout, data){
    override fun convert(helper: BaseViewHolder, item: CityBasic?) {
        helper.setText(R.id.item_search_city_name_tv, item?.location)
        var builder=StringBuilder(item?.admin_area.toString())
        builder.append("省")
        builder.append(" ")
        builder.append(item?.cnty)

        helper.setText(R.id.item_search_province_tv, builder.toString())
    }

}