package com.smile.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smile.baselib.utils.L
import com.smile.weather.MainActivity
import com.smile.weather.R
import com.smile.weather.adapter.DetailContentAdapter
import com.smile.weather.adapter.DetailForecastAdapter
import com.smile.weather.adapter.DetailHourlyAdapter
import com.smile.weather.base.BaseFragment
import com.smile.weather.databinding.DetailHeadContentLayoutBinding
import com.smile.weather.databinding.FragmentDetail3Binding
import com.smile.weather.databinding.FragmentDetailBinding
import com.smile.weather.databinding.HeadAirLayoutBinding
import com.smile.weather.db.*
import com.smile.weather.db.City
import com.smile.weather.entity.*
import com.smile.weather.intent.Api
import com.smile.weather.utils.BackGroundUtils
import com.smile.weather.utils.DisplayUtils
import com.smile.weather.view.BetterGesturesRecyclerView
import com.smile.weather.view.SRecyclerView
import com.smile.weather.vm.DetailViewModel
import com.smile.weather.vm.LocateViewModel

class Detail2Fragment : BaseFragment() {

    private var mCityName = ""
    private var mIsLoadData = false
    private var mNetInt = 0//记录当前天气和未来天气请求 方便最后存入数据库
    private var mNowWeatherJson = ""
    private var mOneDayJson = ""
    private var mCityId = 0

    private var mAllNetInt = 0
    private var mIsRefreshIng = false//是否为手动刷新

    private lateinit var mBinding: FragmentDetail3Binding
    //private lateinit var mHeadBinding: DetailHeadContentLayoutBinding

    private lateinit var mTopHeadContentLayoutBinding: DetailHeadContentLayoutBinding

    lateinit var mHeadAirViewBinding: HeadAirLayoutBinding


    private lateinit var mContentView: BetterGesturesRecyclerView
    private lateinit var mAdapter: DetailContentAdapter
    private lateinit var mContentLayout: CoordinatorLayout

    private lateinit var mHourlyRecyclerView: SRecyclerView
    private lateinit var mForecastRecyclerView: SRecyclerView

    private lateinit var mLeftMore: ImageView
    private lateinit var mRightMore: ImageView


    private val mGson: Gson by lazy {
        Gson()
    }

    private val mCity: City by lazy {
        mCityDao.getCityById(mCityId)
    }

    private val mCityDao: CityDao by lazy {
        AppDataBase.instance.getCityDao()
    }

    private val mCityWeatherDao: CityWeatherDao by lazy {
        AppDataBase.instance.getCityWeatherDao()
    }

    private val mHourlyAdapter: DetailHourlyAdapter by lazy {
        DetailHourlyAdapter(context!!, mHourlyList)
    }

    private lateinit var mWeather6: HeWeather6
    private lateinit var mAir_Now_City: Air_Now_City
    private var mHourlyList = arrayListOf<Hourly>()

    private val mForecastAdapter: DetailForecastAdapter by lazy {
        DetailForecastAdapter(context!!, mForecastList)
    }

    private var mRootView: View? = null

    private var mForecastList = arrayListOf<DailyForecast>()

    private val mDetailViewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this)[DetailViewModel::class.java]

    }

    private val mLocateViewModel: LocateViewModel by lazy {
        ViewModelProviders.of(this)[LocateViewModel::class.java]

    }


    companion object {
        private const val KEY_ID = "ID"

        fun newInstance(pos: Int) = Detail2Fragment().apply {
            arguments = Bundle(1).apply {
                putInt(KEY_ID, pos)
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail3, container, false)
        mBinding.viewModel = mDetailViewModel
        // mHeadBinding=DataBindingUtil.inflate(inflater, R.layout.detail_head_content_layout, container, false);


        mRootView = mBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContentView = mBinding.detailContentViewRlv
        mContentLayout = mBinding.detailContentLayout

        mAdapter = DetailContentAdapter()
        mContentView.layoutManager = LinearLayoutManager(activity)
        mContentView.adapter = mAdapter

        var nowTopView = LayoutInflater.from(activity)
            .inflate(R.layout.detail_head_content_layout, mContentView, false)

        nowTopView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(
                context!!, 200f
            )
        )
        mTopHeadContentLayoutBinding =
            DataBindingUtil.bind<DetailHeadContentLayoutBinding>(nowTopView)!!
        mAdapter.addHeaderView(nowTopView)

        mHeadAirViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(activity),
            R.layout.head_air_layout,
            mContentView,
            false
        )

        mAdapter.addHeaderView(mHeadAirViewBinding.root)


        var hourlyView =
            LayoutInflater.from(activity).inflate(R.layout.head_hourly_layout, mContentView, false)

        mHourlyRecyclerView = hourlyView.findViewById(R.id.head_hourly_rlv)
        //获取头部布局的左右imageView


        mLeftMore = nowTopView.findViewById(R.id.head_now_left_more_img)

        mRightMore = nowTopView.findViewById(R.id.head_now_right_more_img)

        mHourlyRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mHourlyRecyclerView.adapter = mHourlyAdapter


        var forecastView = LayoutInflater.from(activity)
            .inflate(R.layout.head_forecast_layout, mContentView, false)
        mForecastRecyclerView = forecastView.findViewById(R.id.head_forecast_rlv)

        mForecastRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mForecastRecyclerView.adapter = mForecastAdapter

        mAdapter.addHeaderView(hourlyView)

        var title = TextView(context)
        var layoutParams = RecyclerView.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.leftMargin = DisplayUtils.dp2px(context!!, 20f)
        layoutParams.bottomMargin = DisplayUtils.dp2px(context!!, 10f)
        layoutParams.topMargin = DisplayUtils.dp2px(context!!, 10f)
        title.layoutParams = layoutParams
        title.text = "每日"
        title.setTextColor(ContextCompat.getColor(activity!!, R.color.white))
        mAdapter.addHeaderView(title)
        mAdapter.addHeaderView(forecastView)

        var airView =
            LayoutInflater.from(activity).inflate(R.layout.air_view_layout, mContentView, false)
        mAdapter.addHeaderView(airView)


        mCityId = arguments?.getInt(Detail2Fragment.KEY_ID, 0)!!

        mDetailViewModel.refreshing.observe(this, Observer<Boolean> { boolean ->
            if (boolean) {
                mIsRefreshIng = true
                getData()
            }
        })
    }

    private fun initData() {

        mCityName = mCity.name!!
        //mBinding.dlCityNameTv.text = mCity.name
        mBinding.cityName = mCity.name
        mTopHeadContentLayoutBinding.cityName = mCity.name


        /*  if (mCity.isLocal == 1) {

              mBinding.detailHeadTopView.detailHeadLocateImg.visibility = View.VISIBLE
          } else
             mBinding.detailHeadTopView.detailHeadLocateImg.visibility = View.INVISIBLE*/
        //设置是否为本地
        mBinding.isLocate = mCity.isLocal == 1

        mDetailViewModel.getNowData(getParams(mCity.name!!))
        if (!mDetailViewModel.getNowDataForLiveData().hasActiveObservers()) {
            mDetailViewModel.getNowDataForLiveData().observe(this, Observer<WeatherEntity> { data ->
                if (data.HeWeather6[0].status == Api.RESPONSE_STATUS) {
                    mWeather6 = data.HeWeather6[0]
                    mBinding.weather = mWeather6
                    mTopHeadContentLayoutBinding.weather = mWeather6
                    mNowWeatherJson = mGson.toJson(data.HeWeather6[0].now)
                    mContentLayout.setBackgroundResource(BackGroundUtils.getBackGroundByCode(data.HeWeather6[0].now.cond_code.toInt()))
                    Log.e("dandy1", "int " + data.HeWeather6[0].now.cond_code.toInt())
                    mNetInt++
                    if (mNetInt == 2) {
                        insertData()
                    }
                }
                recordRefresh()
            })
        }

        mDetailViewModel.getForecastData(getParams(mCity.name!!))
        if (!mDetailViewModel.getForecastForeLiveData().hasActiveObservers()) {
            mDetailViewModel.getForecastForeLiveData()
                .observe(this, Observer<WeatherEntity> { data ->
                    if (data.HeWeather6[0].status == Api.RESPONSE_STATUS) {
                        mForecastList =
                            data.HeWeather6[0].daily_forecast as ArrayList<DailyForecast>
                        mForecastAdapter.setData(mForecastList)
                        mOneDayJson = mGson.toJson(mForecastList[0])
                        mNetInt++
                        if (mNetInt == 2) {
                            insertData()
                        }
                    }
                    recordRefresh()

                })
        }

        mDetailViewModel.getAirData(getParams(mCity.town!!))
        if (!mDetailViewModel.getAirDataForLiveData().hasActiveObservers()) {
            mDetailViewModel.getAirDataForLiveData().observe(this, Observer<WeatherEntity> { data ->
                if (data.HeWeather6[0].status == Api.RESPONSE_STATUS) {
                    mAir_Now_City = data.HeWeather6[0].airNowCity
                    mHeadAirViewBinding.air = mAir_Now_City
                }
                recordRefresh()

            })
        }

        mDetailViewModel.getHourlyData(getParams(mCity.name!!))
        if (!mDetailViewModel.getHourlyForLiveData().hasActiveObservers()) {
            mDetailViewModel.getHourlyForLiveData().observe(this, Observer<WeatherEntity> { data ->
                if (data.HeWeather6[0].status == Api.RESPONSE_STATUS) {
                    mHourlyList = data.HeWeather6[0].hourlyList as ArrayList<Hourly>
                    mHourlyAdapter.setData(mHourlyList)
                }
                recordRefresh()

            })
        }

        mDetailViewModel.getLifeStyleData(getParams(mCity.name!!))
        if (!mDetailViewModel.getLifeStyleLiveData().hasActiveObservers()) {
            mDetailViewModel.getLifeStyleLiveData().observe(this, Observer { data ->
                run {
                }

            })
        }
        checkIndex()

        mLocateViewModel.getCityInfo(mCityName)
        if (!mLocateViewModel.getCityInfoLiveData().hasActiveObservers()) {
            mLocateViewModel.getCityInfoLiveData().observe(this, Observer { data ->
                L.e("data $data")
            })
        }

    }


    /**
     *记录刷新
     */
    private fun recordRefresh() {
        if (mIsRefreshIng) {
            mAllNetInt++
            if (mAllNetInt >= 4) {
                mIsRefreshIng = false
                mAllNetInt = 0
                mNetInt = 0
                // mBinding.dlRefreshLayout.isRefreshing = false
            }
        }
    }


    private fun getData() {
        mDetailViewModel.getNowData(getParams(mCity.name!!))
        mDetailViewModel.getForecastData(getParams(mCity.name!!))
        mDetailViewModel.getAirData(getParams(mCity.town!!))
        mDetailViewModel.getHourlyData(getParams(mCity.name!!))
        mDetailViewModel.getLifeStyleData(getParams(mCity.name!!))
    }


    /**
     * 保存当前天气和今日天气  地点管理需要用到这些数据
     */
    private fun insertData() {
        val cityWeather = CityWeather(mCityId, mNowWeatherJson, mOneDayJson)
        mCityWeatherDao.insertData(cityWeather)
        mNetInt = 0
    }


    override fun onResume() {
        super.onResume()
        if (mCityId == 0) {
            return
        }
        if (!mIsLoadData) {
            initData()
            mIsLoadData = true
        }
    }

    private fun checkIndex() {
        if (activity is MainActivity) {
            var e = (activity as MainActivity).getDetailIndex(mCityName)
            //  e=DetailIndex.NONE
            when (e) {
                DetailIndex.LEFT -> {
                    mLeftMore.visibility = View.INVISIBLE
                    mRightMore.visibility = View.VISIBLE
                }
                DetailIndex.MIDDLE -> {
                    mLeftMore.visibility = View.VISIBLE
                    mRightMore.visibility = View.VISIBLE
                }
                DetailIndex.RIGHT -> {
                    mLeftMore.visibility = View.VISIBLE
                    mRightMore.visibility = View.INVISIBLE
                }
                DetailIndex.NONE -> {
                    mLeftMore.visibility = View.INVISIBLE
                    mRightMore.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.e("dandy1", "pos $mCityName" + "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.e("dandy1", "pos $mCityName" + "onPause")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


}