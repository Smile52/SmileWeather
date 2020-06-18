# 学习Kotlin实现一个简易天气APP（MVVM+JetPack组件+Databinding）
因为最近在学习Kotlin所以准备通过写一个小项目来试试水，所以这个简易版天气APP出来了
ps：文档整理中
## 效果图
![](https://github.com/Smile52/SmileWeather/blob/master/img-folder/Untitled.gif?raw=true)



## 主要功能
* 定位（百度地图SDK）
* 添加，删除城市
* 获取天气数据（实时天气和未来一周天气，数据由和风天气提供）

## 项目基本介绍
整个项目采用MVVM架构，也是现在比较流行的一个架构，通过ViewModel+LiveData来很好实现低耦合以及更加容易管理生命周期，数据变化驱动UI变化,尤其是加入Databinding（又爱又恨）。
* 网络请求模块还是采用Retrofit+RxJava+LiveData
* 数据库采用Room，此项目展示了基本的增删改差，数据库更新（添加列和新表）
* 温度柱状条是自定义view来实现（主要是计算高度）


## 后续迭代计划
* 修复bug
* 根据天气类型修改背景图片
* 城市管理可以移动城市排列顺序
* 新增一些天气特效


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
[项目地址](https://github.com/Smile52/SmileWeather)
