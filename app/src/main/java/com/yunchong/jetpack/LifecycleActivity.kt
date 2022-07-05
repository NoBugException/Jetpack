package com.yunchong.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.databinding.ActivityLifecycleBinding

class LifecycleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLifecycleBinding>(this@LifecycleActivity, R.layout.activity_lifecycle)
    }
}