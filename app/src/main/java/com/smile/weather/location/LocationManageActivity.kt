package com.smile.weather.location

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.switchMap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smile.baselib.utils.L
import com.smile.weather.R
import com.smile.weather.adapter.LocateManageAdapter
import com.smile.weather.databinding.ActivityLocationManagerBinding
import com.smile.weather.db.AppDataBase
import com.smile.weather.db.CityDao
import com.smile.weather.db.City
import com.smile.weather.db.CityWeatherDao
import com.smile.weather.entity.LocateEntity
import com.smile.weather.utils.RecycleViewDivider
import com.smile.weather.vm.LocateViewModel

/**
 * 地点管理Activity
 */
class LocationManageActivity : com.smile.weather.base.BaseActivity() {

    private lateinit var mCityListView: RecyclerView
    private var mCityList = arrayListOf<LocateEntity>()
    private lateinit var mBindIng: ActivityLocationManagerBinding
    private var mLastId: Int = 0
    private var mIsOpen = false
    private var mDeleteArrayPos = arrayListOf<Int>()
    private lateinit var mDialogBuilder:AlertDialog.Builder
    private val mDao: CityDao by lazy {
        AppDataBase.instance.getCityDao()
    }

    private val mAdapter: LocateManageAdapter by lazy {
        LocateManageAdapter(mCityList)
    }

    private val mLocateViewModel: LocateViewModel by lazy {
        ViewModelProviders.of(this)[LocateViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindIng = DataBindingUtil.setContentView(this, R.layout.activity_location_manager)

        mBindIng.handler = ManagerHandler()
        mCityListView = mBindIng.locateCityRlv
        mBindIng.isOpen = mIsOpen

        initView()
        initData()
    }

    override fun initView() {
        mCityListView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        mCityListView.addItemDecoration(RecycleViewDivider(this, LinearLayoutManager.VERTICAL))
        mCityListView.adapter = mAdapter

        mAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.item_locate_delete_cb -> {
                    mAdapter.data[position].select = !mAdapter.data[position].select
                    // mAdapter.notifyItemChanged(position)
                    if (mAdapter.data[position].select) {
                        mDeleteArrayPos.add(position)
                    }
                }
            }
        }

    }

    override fun initData() {
        super.initData()

        mLocateViewModel.getWeatherList().observe(this, Observer<List<LocateEntity>> { data ->
            mCityList = data as ArrayList<LocateEntity>
            if (mCityList.isNotEmpty()) {
                mAdapter.setNewData(data)
                mLastId = data[data.size - 1].city.id!!
            }

        })


    }

    private fun initSelect() {
        for ((i, locate) in mCityList.withIndex()) {
            mCityList[i].open = mIsOpen
        }
        mAdapter.setNewData(mCityList)

        if (mIsOpen) {
            mBindIng.locateDeleteTv.visibility = View.VISIBLE
        } else
            mBindIng.locateDeleteTv.visibility = View.GONE


    }

    override fun onBackPressed() {
        if (mIsOpen) {
            mIsOpen = false
            initSelect()
        } else
            super.onBackPressed()

    }

    inner class ManagerHandler {

        fun toSearch(view: View) {
            var intent = Intent(this@LocationManageActivity, SearchActivity::class.java)
            intent.putExtra(SearchActivity.KEY_LAST_ID, mLastId)
            startActivity(intent)

        }

        fun back(view: View) {
            finish()
        }

        fun openMore(view: View) {
            mIsOpen = true
            mBindIng.isOpen = mIsOpen
            initSelect()
        }

        fun delete(view: View) {
            //
           // mIsOpen = false
            //mBindIng.isOpen = mIsOpen

            mDialogBuilder=AlertDialog.Builder(this@LocationManageActivity).setTitle("提示")
                .setMessage("是否确定删除")
                .setPositiveButton("确定") { _, _ ->  deleteCityList()}
                .setNegativeButton("取消") { dialog, _ -> dialog.dismiss()}

            mDialogBuilder.create().show()

        }
    }

    private fun deleteCityList(){
        for (mDeleteArrayPo in mDeleteArrayPos) {
            mDao.deleteCity(mCityList[mDeleteArrayPo].city)
        }
    }


}