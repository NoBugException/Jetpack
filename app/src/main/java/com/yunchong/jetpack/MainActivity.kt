package com.yunchong.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.databinding.ActivityMainBinding

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
    }
}