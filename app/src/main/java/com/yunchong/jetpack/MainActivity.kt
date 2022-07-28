package com.yunchong.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.databinding.ActivityMainBinding
import com.yunchong.jetpack.databinding.LoginActivity
import com.yunchong.jetpack.fastsp.FastSharedPreferencesActivity
import com.yunchong.jetpack.lifecycle.LifecycleActivity
import com.yunchong.jetpack.livedata.LiveDataActivity
import com.yunchong.jetpack.liveeventbus.LiveEventBusActivity
import com.yunchong.jetpack.navigation.NavigationActivity
import com.yunchong.jetpack.permissionx.PermissionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
        binding.loginButton.setOnClickListener { // 跳转到登录页面(DataBinding)
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
        binding.permissionxButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, PermissionActivity::class.java))
        }
        binding.lifecycleButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, LifecycleActivity::class.java))
        }
        binding.liveDataButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, LiveDataActivity::class.java))
        }
        binding.liveeventbusButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, LiveEventBusActivity::class.java))
        }
        binding.navigationButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, NavigationActivity::class.java))
        }
        binding.fastspButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, FastSharedPreferencesActivity::class.java))
        }
    }
}