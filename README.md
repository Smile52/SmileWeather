# 学习Kotlin实现一个简易天气APP（MVVM+JetPack组件+Databinding）
因为最近在学习Kotlin所以准备通过写一个小项目来试试水，所以这个简易版天气APP出来了
ps：文档整理中

![描述](https://img-blog.csdnimg.cn/20200710182520403.gif#pic_center)

ps:图片加载不出来可以查看CSDN[博客](https://blog.csdn.net/qq272708698/article/details/103698652)


## 主要功能
* 定位（百度地图SDK）
* 添加，删除城市
* 获取天气数据（实时天气和未来一周天气，数据由和风天气提供）

## 项目基本介绍
整个项目采用MVVM架构，也是现在比较流行的一个架构，通过ViewModel+LiveData来很好实现低耦合以及更加容易管理生命周期，数据变化驱动UI变化,尤其是加入Databinding（又爱又恨）。
* 网络请求模块还是采用Retrofit+RxJava+LiveData
* 数据库采用Room，此项目展示了基本的增删改差，数据库更新（添加列和新表）
* 温度柱状条是自定义view来实现（主要是计算高度）


## 技术点
* ~~retrofit+RxJava结合LiveData使用~~
* retrofit+LiveData实现网络请求
* hilt依赖注入
* 自定义柱状温度View
* CoordinatorLayout自定义behavior
* room数据库的使用以及版本更新
* DataBindIng的基本使用





## 后续迭代计划
* API接口升级（和风天气升级了）
* 添加更多生活数据
* 网络请求采用retrofit+LiveData实现（舍弃RxJava）
* 增加天气动效




## 备注
* DetailFragment是原来版本的详情页面
* DetailFragment2是为目前新版的详情页面

## code对应温度
 * 100  晴天
 * 104 多云

 * 200 风
 * 300  阵雨
 *  301- 304 雷
 * 305 小雨 306 中雨 307 大雨 310-318 暴雨
 * 400 雪
 * 500 雾





#### 备注
觉得对你有帮助的麻烦给一个小星星了。
APK不是最新的
[项目地址](https://github.com/Smile52/SmileWeather)
