package com.yunchong.jetpack.livedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

class UserModelByMediator(application: Application) : AndroidViewModel(application) {

    var mediatorLiveData: MediatorLiveData<User> = MediatorLiveData() // 创建 LiveData 集合对象，管理多个LiveData
    val userLiveData1 = MutableLiveData<User>() // 创建 LiveData 对象1
    val userLiveData2 = MutableLiveData<User>() // 创建 LiveData 对象2
    private var mApplication: Application? = null

    init {
        mediatorLiveData.addSource(userLiveData1) {
            mediatorLiveData.value = it
        }
        mediatorLiveData.addSource(userLiveData2) {
            mediatorLiveData.value = it
        }
        userLiveData1.postValue(User("张三", 2)) // 通知数据变化了
        userLiveData2.postValue(User("李四", 3)) // 通知数据变化了
        mApplication = application
    }

    /**
     * 更新用户信息1
     */
    fun updateUserInfo1() {
        val user = userLiveData1.value?.apply {
            userName = "张三"
            age = (1..100).random()
        }
        userLiveData1.value = user // 或改成 userLiveData1.postValue(user)
    }

    /**
     * 更新用户信息1
     */
    fun updateUserInfo2() {
        val user = userLiveData2.value?.apply {
            userName = "李四"
            age = (1..100).random()
        }
        userLiveData2.value = user // 或改成 userLiveData2.postValue(user)
    }
}