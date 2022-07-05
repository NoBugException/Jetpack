package com.yunchong.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.databinding.ActivityMainBinding
import com.yunchong.jetpack.model.LoginModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
        val loginModel = LoginModel(this, "zhangsan", "123456");
        binding.loginModel = loginModel
    }
}