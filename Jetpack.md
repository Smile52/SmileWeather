# SmileWeather之Jetpack介绍和集成

## 说两句
前面的项目初步介绍我们已经了解了Jetpack是个什么玩意，接下来我们再往前走一点，Jetpack也不是我一两篇文章就能说透的，我在这里只是简单的记录了一下在SmileWeather项目中如何去使用它的。

## 四大分类
### Foundation(基础组件)
* AppCompat：提供了一系列了AppCompat开头的API主要是为了兼容低版本Android
* Android KTX: Kotlin的支持库，优化kotlin来使用Jetpack中的API，让我们更加简洁和高效的来进行Android开发。
* Multidex:为方法数超过 64K 的应用启用多 dex 文件。
* Test:看名字就知道是为了测试用的
还有一些就不列出来了，平时使用的也比较少

### Architecture(架构组件)
* ViewModel:通过对生命周期的感知来处理和UI对接的数据
* Databinding:数据绑定库是一种支持库，借助该库，可以使用声明式将布局中的界面组件绑定到应用中的数据源。
* Lifecycles:管理activity和fragment的生命周期
* LiveData:是一个可观察的数据持有者类。与observable不同处是LiveData是有生命周期感知的。
* Navigation:处理应用内的导航。
* Paging:分页相关的，按需加载数据。
* Room:Room持久性库在SQLite上提供了一个抽象层，帮助开发者更友好、流畅的访问SQLite数据库。
* WorkManager:即使应用程序退出或设备重新启动，也可以轻松地调度预期将要运行的可延迟异步任务。

### Behavior(行为)
说实话这个东西了解的不是很多
* CameraX：帮助开发者简化相机的开发，以前的Android相机这块是真的恶心，并且这个兼容到Android5.0了。
* DownloadManager:下载管理器
* Media & playback(媒体&播放)：用于媒体播放和路由（包括 Google Cast）的向后兼容 API。
* Notifications:通知，提供了向后兼容的API
* Permissions(权限)：用于检查和请求应用权限的兼容性 API。
* Sharing(共享)：提供适合应用操作栏的共享操作。
* Slices(切片)：创建可在应用外部显示应用数据的灵活界面元素。

### UI界面组件
* Animation & Transitions(动画&过度)：提供各种动画
* Emoji(表情符号)：使用户在未更新系统版本的情况下也可以使用表情符号。
* Fragment：组件化界面的基本单位。
* Layout(布局)：xml书写的界面布局或者使用Compose完成的界面。
* Palette(调色板)：从调色板中提取出有用的信息。

## 使用
Jetpack组件都在Google maven仓库里，所以我们在项目的根目录build.gradle文件添加，
我这里是配置了阿里云的镜像，下载代码就快很多。

```
allprojects {
    repositories {
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/jcenter' }
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/google' }
        maven { url 'http://maven.aliyun.com/nexus/content/repositories/gradle-plugin' }
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
然后在app目录下的build.gradle文件添加对应的依赖（这里是SmileWeather中用到的jetpack组件）

```
    def roomVersion = '2.2.5'
    def activity_version = "1.1.0"
    def lifecycle_version = "2.2.0"



    //lifecycle
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    // Optional : Kotlin extension (https://d.android.com/kotlin/ktx)
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

    // liveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    // viewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"

    implementation 'androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02'
    kapt 'androidx.hilt:hilt-compiler:1.0.0-alpha02'

    implementation "com.google.dagger:hilt-android:$hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:$hilt_version"

    implementation "androidx.core:core-ktx:1.3.1"
    implementation "androidx.fragment:fragment-ktx:1.2.5"

    implementation "androidx.activity:activity-ktx:$activity_version"

    // room
    implementation "androidx.room:room-runtime:$roomVersion"
    implementation "androidx.room:room-ktx:$roomVersion"
    kapt "androidx.room:room-compiler:$roomVersion"
    androidTestImplementation "androidx.room:room-testing:$roomVersion"

```
以上差不多让我们的项目可以支持Jetpack，具体需要依赖哪些组件还是要到项目需求上面来。下一篇简单的介绍一下ViewModel和LiveData在项目中的使用。


