package com.yunchong.jetpack

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yunchong.jetpack.databinding.ActivityLiveeventbusBinding
import kotlinx.android.parcel.Parcelize

// Github地址：Github：https://github.com/JeremyLiao/LiveEventBus
// 1、什么是LiveEventBus？
// LiveEventBus是一款Android消息总线，基于LiveData，具有生命周期感知能力，支持Sticky，支持AndroidX，支持跨进程，支持跨APP
// 2、为什么要用LiveEventBus
// 【1】生命周期感知
//  消息随时订阅，自动取消订阅
//  告别消息总线造成的内存泄漏
//  告别生命周期造成的崩溃
// 【2】范围全覆盖的消息总线解决方案
//  进程内消息发送
//  App内，跨进程消息发送
//  App之间的消息发送
//  常用消息总线对比
//  消息总线	        延迟发送	有序接收消息	Sticky	生命周期感知	跨进程/APP	线程分发
//  EventBus	    ❌	    ✅	        ✅	    ❌	        ❌	        ✅
//  RxBus	        ❌	    ❌	        ✅	    ❌	        ❌	        ✅
//  LiveEventBus	✅	    ✅	        ✅	    ✅	        ✅	        ❌

// 3、LiveEventBus的使用：
// 【1】获取Observable
//  LiveEventBus.get("some_key", String::class.java)
//  get 方法获取 Observable，通过 key-value 的方式获取 Observable，key 是String类型，value 可以是String也可以是其他类，value不支持基本数据类型
//  get 方法可以只传入 key：LiveEventBus.get<T>("some_key")，但是必须添加泛型<T>指定value的类型，T是String或其他类
//  get 方法可以只传入 value：LiveEventBus.get(T::class.java)，key默认是类的名称，T是String或其他类
// 【2】进程内发送消息和接收消息：（仅跨线程）
//  接收消息（订阅消息）：LiveEventBus.get("some_key", String::class.java).observe(this) { }
//  发送消息（发布消息）：LiveEventBus.get("some_key", String::class.java).post("zhangsan")
//  发送：void post(T value)
//  延迟发送：void postDelay(T value, long delay)
//  延迟发送，带生命周期：void postDelay(LifecycleOwner sender, T value, long delay)
//  有序发送：void postOrderly(T value)
//  支持一对多：发送一条消息，多方收到消息
// 【3】App内发送消息，跨进程使用（跨线程、跨进程）
//  void postAcrossProcess(T value)
//  跨进程不仅支持基本数据类型消息的跨进程发送，还支持Serializable和Parcelable类型消息的跨进程发送
//  java需要继承Serializable或Parcelable（推荐）类
//  如果是Kotlin的话，首先需要导入插件： id 'kotlin-android-extensions' 或 id 'kotlin-parcelize' 或 id 'org.jetbrains.kotlin.android.extensions'
//  然后添加注解：@Parcelize，并且继承 Parcelable
//    @Parcelize
//    class DemoEvent(val content: String) : Parcelable
//  支持一对多：发送一条消息，多方收到消息
// 【4】App之间发送消息（跨线程、跨进程、跨APP）
//  void postAcrossApp(T value)
// 【5】消息订阅
//  以生命周期感知模式订阅消息：void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer)
//  Forever模式订阅消息，需要调用removeObserver取消订阅：void observeForever(@NonNull Observer<T> observer)
//  取消订阅消息：void removeObserver(@NonNull Observer<T> observer)
//  【6】Sticky模式订阅消息
//  Sticky模式：void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer)
//  Sticky模式Forever订阅消息：void observeStickyForever(@NonNull Observer<T> observer)
//  通过测试，Sticky不支持跨进程、不支持跨App，只能在进程间使用
// 【6】配置
//        // LiveEventBus 配置
//        // lifecycleObserverAlwaysActive
//        // true：整个生命周期（从onCreate到onDestroy）都可以实时收到消息
//        // false：激活状态（Started）可以实时收到消息，非激活状态（Stoped）无法实时收到消息，需等到Activity重新变成激活状态，方可收到消息
//        LiveEventBus
//            .config()
//            .enableLogger(false) // 日志开关，默认是true
//            .lifecycleObserverAlwaysActive(true) // 配置LifecycleObserver（如Activity）接收消息的模式（默认值true）
//            .autoClear(false) // 是否自动清除LiveEvent以释放内存，默认false


class LiveEventBusActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLiveeventbusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLiveeventbusBinding>(this@LiveEventBusActivity, R.layout.activity_liveeventbus)
        LiveEventBus.get("some_key", DemoEvent::class.java).observeSticky(this){
            binding.text.text = it.content
        }
        binding.postMessage.setOnClickListener {
            LiveEventBus.get("some_key", DemoEvent::class.java).post(DemoEvent("Zhangsana"))
        }
    }

    @Parcelize
    class DemoEvent(val content: String) : Parcelable
}