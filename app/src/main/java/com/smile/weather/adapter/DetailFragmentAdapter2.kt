package com.smile.weather.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.smile.baselib.utils.L
import com.smile.weather.DetailFragment
import com.smile.weather.base.BaseActivity

class DetailFragmentAdapter2 (activity: BaseActivity, var ids:ArrayList<Int>):FragmentStateAdapter(activity){



    fun setData( data:ArrayList<Int>){
        ids= data
        notifyDataSetChanged()
    }


     override fun getItemCount(): Int {
         return if (ids!=null)ids.size else 0
     }

     override fun createFragment(position: Int): Fragment {
         return DetailFragment.newInstance(ids[position])
     }







}