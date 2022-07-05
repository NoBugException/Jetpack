package com.yunchong.jetpack.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import com.yunchong.jetpack.PermissionActivity
import com.yunchong.jetpack.utils.startActivity

/**
 * 登录Model
 */
class LoginModel(context: Context, accountName : String, password : String) {

    val context: Context = context;
    val accountNameField = ObservableField<String>(accountName)
    val passwordField = ObservableField<String>(password)

    /**
     * 输入账号变化时执行
     */
    fun accountNameChanged() {
        Log.d("yunchong", accountNameField.get() as String)
    }

    /**
     * 输入密码时执行
     */
    fun passwordChanged() {
        Log.d("yunchong", passwordField.get() as String)
    }

    /**
     * 登录
     */
    fun login() {
        if ("zhangsan" == accountNameField.get() && "123456" == passwordField.get()) {
            Toast.makeText(context, "登录成功...", Toast.LENGTH_SHORT).show()
            startActivity<PermissionActivity>(context) {
                putExtra("acountName", accountNameField.get())
                putExtra("password", passwordField.get())
            }
        } else {
            Toast.makeText(context, "登录失败...", Toast.LENGTH_SHORT).show()
        }
    }
}