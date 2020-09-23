package com.smile.weather.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.smile.baselib.base.BaseActivity
import com.smile.weather.base.BaseFragment

class DetailFragmentAdapter (activity: BaseActivity, private var fragments:ArrayList<BaseFragment>):FragmentStateAdapter(activity){

    private val fragmentFactory = activity.supportFragmentManager.fragmentFactory
    private val classLoader = activity.classLoader
    private val pageIds= fragments.map { it.hashCode().toLong()}



    fun setData( data:ArrayList<BaseFragment>){
        fragments= data
       notifyDataSetChanged()

    }

   // fun getFragmentName(position: Int) = fragments[position].fragmentClass.simpleName

     override fun getItemCount(): Int {
         return if (fragments!=null)fragments.size else 0
     }

     override fun createFragment(position: Int): Fragment {
         return fragments[position]
     }

    /**
     *重写下面两个方法处理adapter移除fragment缓存问题
     */
    override fun getItemId(position: Int): Long {
        return fragments[position].hashCode().toLong() // make sure notifyDataSetChanged() works
    }

    override fun containsItem(itemId: Long): Boolean {
        return pageIds.contains(itemId)
    }















}