package com.yunchong.jetpack.databinding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.R
import com.yunchong.jetpack.ScopedActivity

/**
 * databinding演示，MVVM架构，双向绑定
 */
class LoginActivity : ScopedActivity() {

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