package com.smile.weather.adapter

import android.widget.CheckBox
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.smile.weather.R
import com.smile.weather.db.City
import com.smile.weather.entity.LocateEntity
import com.smile.weather.utils.DateUtils
import com.smile.weather.utils.IconUtils
import java.lang.StringBuilder

class LocateManageAdapter( data:ArrayList<LocateEntity>):BaseQuickAdapter<LocateEntity,BaseViewHolder>( R.layout.item_locate_layout, data){

    override fun convert(helper: BaseViewHolder, item: LocateEntity?) {

            helper.setText(R.id.item_locate_name_tv, item?.city?.name)
            if (item?.city!!.isLocal==1){
                helper.setVisible(R.id.item_locate_l_img, true)
            }else{
                helper.setGone(R.id.item_locate_l_img, false)
            }
            if (item.open){
                helper.setVisible(R.id.item_locate_delete_cb, true)

                helper.setChecked(R.id.item_locate_delete_cb, item.select)
                helper.addOnClickListener(R.id.item_locate_delete_cb)
                if (item.city.isLocal==1){
                    var cb=helper.getView<CheckBox>(R.id.item_locate_delete_cb)
                    cb.setButtonDrawable(R.drawable.icon_locate_un_checked)
                    cb.isClickable=false

                }

            }else{
                helper.setGone(R.id.item_locate_delete_cb, false)
            }
            if(item.now!=null){
                helper.setText(R.id.item_locate_temp_tv, item.now?.tmp+" °")
                helper.setImageResource(R.id.item_locate_icon_img, IconUtils.getSmallIcon(item.now?.cond_code!!.toInt()))
            }
            if (item.oneDay!=null){
                var builder=StringBuilder()
                builder.append(item.oneDay?.tmp_max)
                builder.append("° ")
                builder.append("/")
                builder.append(item.oneDay?.tmp_min)
                builder.append("°")
                helper.setText(R.id.item_locate_temp_a_tv, builder.toString())
            }

        helper.setText(R.id.item_locate_time_tv, DateUtils.getCurrentTime())



    }



}