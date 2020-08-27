package com.smile.weather

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.smile.baselib.utils.L
import com.smile.weather.adapter.DetailFragmentAdapter
import com.smile.weather.base.BaseActivity
import com.smile.weather.base.BaseFragment
import com.smile.weather.databinding.ActivityMainBinding
import com.smile.weather.db.AppDataBase
import com.smile.weather.db.CityDao
import com.smile.weather.db.City
import com.smile.weather.entity.DetailIndex
import com.smile.weather.location.LocationManageActivity
import com.smile.weather.location.SearchActivity
import com.smile.weather.ui.Detail2Fragment
import com.smile.weather.utils.PermissionUtils
import com.smile.weather.vm.LocateViewModel
import com.smile.weather.vm.MainViewModel

open class MainActivity : BaseActivity() {


    private lateinit var mViewModel: MainViewModel
    private lateinit var mLocationClient: LocationClient
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mListCity: List<City>

    private var mCityNameArray = arrayOf<String?>()
    private var mMapFragment = mutableMapOf<String, Detail2Fragment>()
    private var mFragments = arrayListOf<BaseFragment>()
    private var mIsAddCity = false


    private val mAdapter: DetailFragmentAdapter by lazy {
        DetailFragmentAdapter(this, mFragments)
    }
    private val mViewPager: ViewPager2 by lazy {
        mBinding.mainContentPage
    }

    private val mDao: CityDao by lazy {
        AppDataBase.instance.getCityDao()
    }

    companion object {
        const val KEY_IS_ADD = "key_is_add"
    }

    private val mLocateViewModel: LocateViewModel by lazy {
        ViewModelProviders.of(this)[LocateViewModel::class.java]

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.handler = MainHandler()

        mViewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        PermissionUtils.location(this) {
            L.e("获取定位权限")
            mLocationClient = LocationClient(this)
            val option = LocationClientOption()
            option.isOpenGps = true // 打开gps
            option.setCoorType("bd09ll") // 设置坐标类型
            option.setIsNeedAddress(true)

            //设置locationClientOption
            mLocationClient.locOption = option

            //注册LocationListener监听器
            val locationListener = MyLocationListener()
            mLocationClient.registerLocationListener(locationListener)
            mLocationClient.start()
        }
        mViewPager.adapter = mAdapter

        val list = mDao.getAll()
        list.observe(this, Observer<List<City>> { data ->
            mListCity = data
            initData()
        })

    }

    override fun initData() {
        super.initData()
        mFragments = arrayListOf()
        mCityNameArray = arrayOfNulls(mListCity.size)

        for ((i, city) in mListCity.withIndex()) {
            if (mMapFragment[city.name] == null) {
                val fragment = Detail2Fragment.newInstance(city.id!!)
                mFragments.add(fragment)
            }
            mCityNameArray[i] = city.name!!
        }
        //如果没有则表示当前没有数据，所以创建一个空的fragment
        if (mFragments.isEmpty()) {
            mFragments.add(Detail2Fragment.newInstance(0))
        }

        mAdapter.setData(mFragments)
        if (mListCity.size == 1) {
            mViewModel.setRefresh(mListCity[0].id!!)

        }
        //如果为添加城市则跳转到对应fragment
        if (mIsAddCity) {
            mViewPager.setCurrentItem(mAdapter.itemCount - 1, true)
            mIsAddCity = false
        }
        mBinding.citySize = mListCity.size

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //标记为添加新城市了
        if (intent!!.getBooleanExtra(KEY_IS_ADD, false)) {
            mIsAddCity = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient.stop()
    }

    override fun initView() {
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    inner class MyLocationListener : BDAbstractLocationListener() {
        override fun onReceiveLocation(p0: BDLocation?) {

            if (p0 == null) {
                return
            }
            if (p0.addrStr != null) {
                //判断本地有没有数据，有数据就判断城市是否改变
                val city1 = mDao.getLocalCity()
                if (city1 == null) {
                    mLocateViewModel.getCityInfo(p0.address.district)
                    //获取城市信息
                    mLocateViewModel.getCityInfoLiveData()
                        .observe(this@MainActivity, Observer { data ->
                            run {
                                L.e("定位后${data.location?.get(0)} ")

                                val city = City(
                                    1, p0.address.district, p0.address.city, 1, ""
                                    , data.location?.get(0)!!.id
                                )
                                mDao.insertCity(city)
                            }
                        })


                } else if (p0.address.district != city1.name) {
                    mLocateViewModel.getCityInfo(p0.address.district)
                    mLocateViewModel.getCityInfoLiveData()
                        .observe(this@MainActivity, Observer { data ->
                            run {

                            }
                        })

                    val city = City(1, p0.address.district, p0.address.city, 1, "", "")
                    mDao.insertCity(city)
                }
            }

        }

    }

    open fun getDetailIndex(name: String): DetailIndex {
        val lastIndex = mCityNameArray.lastIndex
        val index = mCityNameArray.indexOf(name)
        if (mCityNameArray.size == 1) {
            return DetailIndex.NONE
        }
        return when (index) {
            lastIndex -> {
                DetailIndex.RIGHT
            }
            0 -> {
                DetailIndex.LEFT
            }
            else -> DetailIndex.MIDDLE
        }

    }

    inner class MainHandler {
        fun locationClick(view: View) {
            startActivity(Intent(this@MainActivity, LocationManageActivity::class.java))
        }

        fun searchCity(view: View) {

            val intent = Intent(this@MainActivity, SearchActivity::class.java)
            if (mListCity.isEmpty()) {
                intent.putExtra(SearchActivity.KEY_LAST_ID, 0)

            } else {
                intent.putExtra(SearchActivity.KEY_LAST_ID, mListCity.last().id)

            }
            startActivity(intent)
        }
    }

}
