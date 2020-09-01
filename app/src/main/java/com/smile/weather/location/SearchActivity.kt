package com.smile.weather.location

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smile.baselib.entity.BaseResult
import com.smile.baselib.utils.L
import com.smile.baselib.utils.ToastUtil
import com.smile.weather.MainActivity
import com.smile.weather.R
import com.smile.weather.adapter.SearchAdapter
import com.smile.weather.base.BaseActivity
import com.smile.weather.config.Config
import com.smile.weather.databinding.ActivitySearchBinding
import com.smile.weather.db.AppDataBase
import com.smile.weather.db.City
import com.smile.weather.entity.CityBasic
import com.smile.weather.entity.CityEntity
import com.smile.weather.entity.Location
import com.smile.weather.vm.LocateViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity :BaseActivity(){


    companion object{
        const val KEY_LAST_ID="last_id"
    }


    private var mCityDaoList= listOf<City>()

    private val mLocateViewModel: LocateViewModel by viewModels()
    private var mCityList= arrayListOf<Location>()

    private lateinit var mRecyclerView:RecyclerView

    private val mAdapter by lazy {
        SearchAdapter(mCityList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindIng:ActivitySearchBinding=DataBindingUtil.setContentView(this, R.layout.activity_search)
        bindIng.handler=SearchHandler()
        mRecyclerView=bindIng.searchRlv
        initView()
        initData()
    }
    override fun initView() {
        mRecyclerView.layoutManager=LinearLayoutManager(this)
        mRecyclerView.adapter=mAdapter
        initListener()
    }


    override fun initData() {
        super.initData()
        mLocateViewModel.getCityList().observe(this, Observer<List<City>>{
            data-> mCityDaoList=data
        })
    }

    private fun initListener(){
        mAdapter.setOnItemClickListener  { _, _, position ->
            var c=mCityList[position]

            var lastId=intent.getIntExtra(KEY_LAST_ID,0)

            lastId++
            if (mCityDaoList.isEmpty()){
                var city=City(lastId,c.name, c.adm1,1,"",c.id)

                AppDataBase.instance.getCityDao().insertCity(city)
            }else{
                if (cityExist(c.name)){
                    ToastUtil.showMessage("城市已经添加了")
                    return@setOnItemClickListener
                }
                var city=City(lastId,c.name, c.adm1,0,"",c.id)
                AppDataBase.instance.getCityDao().insertCity(city)
            }

            val  intent=Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.KEY_IS_ADD,true)
            startActivity(intent)
        }
    }

   private fun searchCity(content:String){
      // mLocateViewModel.searchCity(getParams(content))
      /*  if (!mLocateModel.getSearchLiveData().hasActiveObservers()){
            mLocateModel.getSearchLiveData().observe(this, Observer<CityEntity>{
                data->
                mCityList= data.HeWeather6[0].basics as ArrayList<CityBasic>
                mAdapter.setNewData(mCityList)
            })
        }*/
       mLocateViewModel.searchCity(content).observe(this, Observer<BaseResult<List<Location>>> { data ->
           run {
               mCityList = data.location as ArrayList<Location>
               mAdapter.setNewData(mCityList)
           }
       })


    }


    private fun getParams(content: String):Map<String,String>{
        return mutableMapOf("location" to content,"key" to Config.API_KEY ,"lang" to "zh")
    }

    private fun cityExist(name:String):Boolean{
        for (city in mCityDaoList){
            if (name == city.name){
                return true
            }
        }
        return false
    }


    inner class SearchHandler{
        fun  search(view:View,content:String){
            searchCity(content)
        }

        fun back(view: View){
            finish()
        }
    }



}