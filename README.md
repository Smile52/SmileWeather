# SmileWeather之项目简单介绍
因为最近在学习Kotlin所以准备通过写一个小项目来试试水，所以这个简易版天气APP出来了
ps：文档整理中

![描述](https://img-blog.csdnimg.cn/20200710182520403.gif#pic_center)

ps:图片加载不出来可以查看CSDN[博客](https://blog.csdn.net/qq272708698/article/details/103698652)


## Jetpack是什么
总结成一句话，Jetpack就是Google为了帮助Android开发者更加轻松和高效开发Android应用程序推出的一系列工具。主要分为四大类：Foundation（基础组件）、Architecture（架构组件）、Behavior（行为）以及UI（界面组件）。


### 什么是MVVM
* MVVM对应的就是Model,View,ViewModel。
* Model:负责获取数据模型
* View:负责页面的绘制和与用户的交互
* ViewModel:将Model与View进行关联，负责处理一些业务逻辑和处理数据，然后通知View层刷新。当然View层的交互也同样可以影响到ViewModel，这种实现可能就需要通过框架了（Databinding）

### 简单了解一下Databinding
Databinding是属于Jetpack组件中一部分，它的使命主要是将数据与UI进行绑定，既支持单向绑定，也支持双向绑定。怎么理解呢？单向绑定就是数据绑定了UI，当数据改变的时候，UI改变；双向绑定就是UI改变的时候数据也会改变，举个栗子，EditTextView，用户输入内容了，具体的内容会映射到绑定的数据体，这个在该项目中已经使用了。多说一句单向绑定和双向绑定的区别就是双向绑定前面多了一个=  `android:text="@={ viewModel.mInputCity }"`


当然Databinding还支持很多自定义方法来完成我们的一些奇葩需求。


### 技术点分析
本项目采用Kotlin编写。
* 项目基础架构MVVM
* 网络请求:Retrofit+LiveData
* 数据库:Room
* 依赖注入:Hint
* 自定义Databinding
* 自定义温度柱状条


下一篇文章《SmileWeather之Jetpack介绍和集成》



#### 备注
觉得对你有帮助的麻烦给一个小星星了。文章陆续更新
[项目地址](https://github.com/Smile52/SmileWeather)
