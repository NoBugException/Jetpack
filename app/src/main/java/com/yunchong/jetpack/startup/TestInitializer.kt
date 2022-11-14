package com.yunchong.jetpack.startup

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import java.util.*

class TestInitializer : Initializer<TestInstance> {

    override fun create(context: Context): TestInstance {
        Log.e("StartUpDemo", "===TestInitializer create===");
        return TestInstance.getInstance();
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        Log.e("StartUpDemo", "===TestInitializer dependencies===");
        return Collections.emptyList()
    }
}