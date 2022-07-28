package com.yunchong.jetpack.livedata

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserModel(application: Application) : ViewModel() {

    val userLiveData = MutableLiveData<User>() // 创建 LiveData 对象
    private var mApplication: Application? = null

    init {
        userLiveData.postValue(User("张三", 2)) // 通知数据变化了
        mApplication = application
    }

    /**
     * 更新用户信息
     */
    fun updateUserInfo() {
        val user = userLiveData.value?.apply {
            userName = "张三"
            age = (1..100).random()
        }
        userLiveData.value = user // 或改成 userLiveData.postValue(user)

    }
}