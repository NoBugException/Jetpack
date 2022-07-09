package com.yunchong.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jeremyliao.liveeventbus.core.LiveEvent
import com.yunchong.jetpack.databinding.ActivityLiveeventbusBinding


class LiveEventBusActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLiveeventbusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLiveeventbusBinding>(this@LiveEventBusActivity, R.layout.activity_liveeventbus)

        // Github：https://github.com/JeremyLiao/LiveEventBus

        // 引入依赖
        // 非AndroidX：implementation 'io.github.jeremyliao:leb-processor-gson:x.x.x'
        // AndroidX：implementation 'io.github.jeremyliao:lebx-processor-gson:x.x.x'

        // 通过name获取Observable
        // Observable<T> get(@NonNull String key, @NonNull Class<T> type)
        // Observable<Object> get(@NonNull String key)

        // 通过event type获取Observable
        // <T extends LiveEvent> Observable<T> get(@NonNull Class<T> eventType)

        // 进程内发送消息
        // void post(T value)
        // void postDelay(T value, long delay)
        // void postDelay(LifecycleOwner sender, T value, long delay)
        // void postOrderly(T value)

        // App内发送消息，跨进程使用
        // void postAcrossProcess(T value)

        // App之间发送消息
        // void postAcrossApp(T value)

        // 以广播的形式发送一个消息
        // 需要跨进程、跨APP发送消息的时候调用该方法，建议尽量使用postAcrossProcess、postAcrossApp
        // void broadcast(T value, boolean foreground, boolean onlyInApp)

        // 以生命周期感知模式订阅消息
        // void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer)

        // 以Forever模式订阅和取消订阅消息，需要调用removeObserver取消订阅
        // void observeForever(@NonNull Observer<T> observer)

        // 取消订阅消息
        // void removeObserver(@NonNull Observer<T> observer)

        // Sticky模式订阅消息
        // void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer)

        // Sticky模式Forever订阅消息，需要调用removeObserver取消订阅
        // void observeStickyForever(@NonNull Observer<T> observer)

        // 跨进程消息
        // 支持对基本数据类型消息的跨进程发送：int、float、long、boolean、double、String
        // 支持Serializable和Parcelable类型消息的跨进程发送：SerializableProcessor、ParcelableProcessor
        // 支持Bean类型消息的跨进程发送：提供GsonProcessor以Gson方式提供支持、需要用注解@IpcConfig指定GsonProcessor（@IpcConfig(processor = GsonProcessor.class)）

        // 支持自定义扩展：实现自定义Processor，实现Processor接口、用注解@IpcConfig指定自定义Processor

        // 在Application.onCreate方法中配置：https://github.com/JeremyLiao/LiveEventBus/blob/master/docs/config.md
        // LiveEventBus
        //        .config()
        //        ...

        // 在组件化中使用LiveEventBus：https://github.com/JeremyLiao/android-modular

        // 混淆：Android
        //-dontwarn com.jeremyliao.liveeventbus.**
        //-keep class com.jeremyliao.liveeventbus.** { *; }
        //-keep class android.arch.lifecycle.** { *; }
        //-keep class android.arch.core.** { *; }

        // 混淆：AndroidX
        //-dontwarn com.jeremyliao.liveeventbus.**
        //-keep class com.jeremyliao.liveeventbus.** { *; }
        //-keep class androidx.lifecycle.** { *; }
        //-keep class androidx.arch.core.** { *; }




//        // 以生命周期感知模式订阅消息
//        LiveEventBus.get("some_key", String::class.java)
//            .observe(this@LiveEventBusActivity, Observer {
//
//        })
//
//        // 以Forever模式订阅消息
//        LiveEventBus.get("some_key", String::class.java)
//            .observeForever(Observer {
//
//            })
//
//        // 不定义消息直接发送
//        LiveEventBus.get("some_key").post(some_value);
//
//        // 先定义消息，再发送消息
//        LiveEventBus.get(DemoEvent::class.java).post(DemoEvent("Hello world"))

    }

    class DemoEvent(val content: String) : LiveEvent
}