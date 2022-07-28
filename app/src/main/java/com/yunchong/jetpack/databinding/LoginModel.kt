package com.yunchong.jetpack.databinding

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.databinding.ObservableField
import com.yunchong.alltoast.AllToast
import com.yunchong.jetpack.R


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
            AllToast.with(context)
                .text("登录成功...")
                .gravity(Gravity.CENTER, 0, 0)
                .textSize(20F) //文本大小，单位是sp
                .radius(5F)//单位为dp
                .duration(Toast.LENGTH_LONG) //时长
                .show()
        } else {
            AllToast.with(context)
                .text("登录失败...")
                .gravity(Gravity.CENTER, 0, 0)
                .textSize(20F) //文本大小，单位是sp
                .radius(5F)//单位为dp
                .duration(Toast.LENGTH_LONG) //时长
                .show()
        }
    }
}