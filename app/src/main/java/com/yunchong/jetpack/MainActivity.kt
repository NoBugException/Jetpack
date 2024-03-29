package com.yunchong.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.startup.AppInitializer
import com.yunchong.jetpack.databinding.ActivityMainBinding
import com.yunchong.jetpack.databinding.LoginActivity
import com.yunchong.jetpack.fastsp.FastSharedPreferencesActivity
import com.yunchong.jetpack.lifecycle.LifecycleActivity
import com.yunchong.jetpack.livedata.LiveDataActivity
import com.yunchong.jetpack.liveeventbus.LiveEventBusActivity
import com.yunchong.jetpack.navigation.NavigationActivity
import com.yunchong.jetpack.room.RoomActivity
import com.yunchong.jetpack.permissionx.PermissionActivity
import com.yunchong.jetpack.startup.SdkInitializer

class MainActivity : ScopedActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 手动初始化SdkInitializer，当然，在这之前，需要在AndroidManifest中，将SdkInitializer的node值改成remove
        AppInitializer.getInstance(this).initializeComponent(SdkInitializer::class.java)

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
        binding.pagingButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, RoomActivity::class.java))
        }
    }
}