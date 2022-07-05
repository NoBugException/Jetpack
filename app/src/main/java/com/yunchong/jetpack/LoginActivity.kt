package com.yunchong.jetpack

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yunchong.jetpack.databinding.ActivityLoginBinding
import com.yunchong.jetpack.model.LoginModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this@LoginActivity, R.layout.activity_login)
        val loginModel = LoginModel(this@LoginActivity, "zhangsan", "123456");
        binding.loginModel = loginModel
    }
}