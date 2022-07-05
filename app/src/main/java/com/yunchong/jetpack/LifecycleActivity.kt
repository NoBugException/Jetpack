package com.yunchong.jetpack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.yunchong.jetpack.databinding.ActivityLifecycleBinding

class LifecycleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLifecycleBinding>(this@LifecycleActivity, R.layout.activity_lifecycle)
        lifecycle.addObserver(MyObserver())
    }

    override fun onResume() {
        Log.d("MyObserver", "before onResume")
        super.onResume()
        Log.d("MyObserver", "after onResume")
    }

    override fun onPause() {
        Log.d("MyObserver", "before onPause")
        super.onPause()
        Log.d("MyObserver", "after onPause")
    }

    class MyObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume() {
            Log.d("MyObserver", "Lifecycle call onResume")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause() {
            Log.d("MyObserver", "Lifecycle call onPause")
        }
    }
}