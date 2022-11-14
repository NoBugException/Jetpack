package com.yunchong.jetpack

import android.app.Application
import android.content.Context
import android.util.Log
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yunchong.fastsharedpreferences.FastSharedPreferences

class JetpackApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.d("StartUpDemo", "===JetpackApplication attachBaseContext===")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("StartUpDemo", "===JetpackApplication onCreate===")
        instance = this

        // LiveEventBus 配置
        // lifecycleObserverAlwaysActive
        // true：整个生命周期（从onCreate到onDestroy）都可以实时收到消息
        // false：激活状态（Started）可以实时收到消息，非激活状态（Stoped）无法实时收到消息，需等到Activity重新变成激活状态，方可收到消息
        LiveEventBus
            .config()
            .enableLogger(false) // 日志开关，默认是true
            .lifecycleObserverAlwaysActive(true) // 配置LifecycleObserver（如Activity）接收消息的模式（默认值true）
            .autoClear(false) // 是否自动清除LiveEvent以释放内存，默认false

        FastSharedPreferences.init(this)
    }

    companion object {
        var instance: JetpackApplication? = null
            private set
    }
}