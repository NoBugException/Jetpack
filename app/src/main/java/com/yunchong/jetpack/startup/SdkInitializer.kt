package com.yunchong.jetpack.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import java.util.*

class SdkInitializer : Initializer<Unit> {
    /**
     * 初始化SDK或者对象的方法
     */
    override fun create(context: Context) {
        Log.e("StartUpDemo", "===SdkInitializer create===");
    }

    /**
     * SdkInitializer初始化使用的依赖
     * 如果没有：直接返回空列表即可
     * 如果有：比如TestInitializer，自动优先初始化TestInitializer之后才会初始化 SdkInitializer
     */
    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        Log.e("StartUpDemo", "===SdkInitializer dependencies===");
        return return mutableListOf(TestInitializer::class.java)
    }
}