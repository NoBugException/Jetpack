package com.yunchong.jetpack.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.R

/**
 * databinding演示，MVVM架构，双向绑定
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this@LoginActivity,
            R.layout.activity_login
        )
        val loginModel = LoginModel(this@LoginActivity, "zhangsan", "123456");
        binding.loginModel = loginModel
    }
}